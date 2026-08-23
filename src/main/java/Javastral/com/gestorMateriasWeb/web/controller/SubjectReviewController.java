package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.SubjectReview;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectReviewRepository;
import Javastral.com.gestorMateriasWeb.model.repository.UserRepository;
import Javastral.com.gestorMateriasWeb.web.controller.request.ReviewItemDTO;
import Javastral.com.gestorMateriasWeb.web.controller.request.ReviewSummaryDTO;
import Javastral.com.gestorMateriasWeb.web.controller.request.ReviewUpsertDTO;
import Javastral.com.gestorMateriasWeb.web.controller.request.ReviewsResponseDTO;
import Javastral.com.gestorMateriasWeb.web.controller.response.Error;
import Javastral.com.gestorMateriasWeb.web.controller.response.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class SubjectReviewController {

    private static final String SORT_LATEST = "latest";
    private static final String SORT_RATING_DESC = "rating_desc";

    private final SubjectReviewRepository subjectReviewRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public SubjectReviewController(
            SubjectReviewRepository subjectReviewRepository,
            SubjectRepository subjectRepository,
            UserRepository userRepository
    ) {
        this.subjectReviewRepository = subjectReviewRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/subjects/{subjectId}/reviews")
    ResponseEntity<Response<ReviewsResponseDTO>> getSubjectReviews(
            @PathVariable long subjectId,
            @RequestParam(name = "sort", defaultValue = SORT_LATEST) String sort
    ) {
        if (!subjectRepository.existsById(subjectId)) {
            var msg = "La materia con id " + subjectId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        String normalizedSort = normalizeSort(sort);
        if (!isAllowedSort(normalizedSort)) {
            return invalidSortResponse();
        }

        List<SubjectReview> reviews = SORT_RATING_DESC.equals(normalizedSort)
                ? subjectReviewRepository.findBySubjectIdOrderByRatingDescCreatedAtDesc(subjectId)
                : subjectReviewRepository.findBySubjectIdOrderByCreatedAtDesc(subjectId);

        var dto = new ReviewsResponseDTO(
                ReviewSummaryDTO.fromProjection(subjectReviewRepository.summarizeBySubjectId(subjectId)),
                ReviewItemDTO.fromSubjectReviews(reviews)
        );

        return ResponseEntity.ok(new Response<>(dto));
    }

    @PutMapping("/subjects/{subjectId}/reviews/me")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<Response<ReviewItemDTO>> upsertMySubjectReview(
            @PathVariable long subjectId,
            @Valid @RequestBody ReviewUpsertDTO body
    ) {
        var subjectOpt = subjectRepository.findById(subjectId);
        if (subjectOpt.isEmpty()) {
            var msg = "La materia con id " + subjectId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        var userOpt = currentUser();
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.notFound("Usuario autenticado no encontrado."));
        }

        var user = userOpt.get();
        var review = subjectReviewRepository.findByUserIdAndSubjectId(user.getId(), subjectId)
                .orElse(new SubjectReview(user, subjectOpt.get(), body.getRating(), body.getComment().trim()));

        review.setRating(body.getRating());
        review.setComment(body.getComment().trim());

        var saved = subjectReviewRepository.save(review);
        return ResponseEntity.ok(new Response<>(ReviewItemDTO.fromSubjectReview(saved)));
    }

    @GetMapping("/subjects/{subjectId}/reviews/me")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<Response<ReviewItemDTO>> getMySubjectReview(@PathVariable long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            var msg = "La materia con id " + subjectId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        var userOpt = currentUser();
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.notFound("Usuario autenticado no encontrado."));
        }

        var review = subjectReviewRepository.findByUserIdAndSubjectId(userOpt.get().getId(), subjectId);
        if (review.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.notFound("No existe review del usuario para la materia indicada."));
        }

        return ResponseEntity.ok(new Response<>(ReviewItemDTO.fromSubjectReview(review.get())));
    }

    @DeleteMapping("/subjects/{subjectId}/reviews/me")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<Response<String>> deleteMySubjectReview(@PathVariable long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            var msg = "La materia con id " + subjectId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        var userOpt = currentUser();
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.notFound("Usuario autenticado no encontrado."));
        }

        var reviewOpt = subjectReviewRepository.findByUserIdAndSubjectId(userOpt.get().getId(), subjectId);
        if (reviewOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.notFound("No existe review del usuario para la materia indicada."));
        }

        subjectReviewRepository.delete(reviewOpt.get());
        return ResponseEntity.ok(new Response<>("Review eliminada correctamente."));
    }

    private Optional<Javastral.com.gestorMateriasWeb.model.entity.UserEntity> currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    private static String normalizeSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return SORT_LATEST;
        }
        return sort.trim().toLowerCase(Locale.ROOT);
    }

    private static boolean isAllowedSort(String sort) {
        return SORT_LATEST.equals(sort) || SORT_RATING_DESC.equals(sort);
    }

    private ResponseEntity<Response<ReviewsResponseDTO>> invalidSortResponse() {
        var error = new Error(
                "Parametro sort invalido. Valores permitidos: latest, rating_desc.",
                HttpStatus.BAD_REQUEST.toString()
        );
        return ResponseEntity.badRequest().body(new Response<>(error));
    }
}
