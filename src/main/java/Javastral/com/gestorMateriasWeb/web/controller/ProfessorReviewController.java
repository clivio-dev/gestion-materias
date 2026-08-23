package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.ProfessorReview;
import Javastral.com.gestorMateriasWeb.model.repository.ProfessorRepository;
import Javastral.com.gestorMateriasWeb.model.repository.ProfessorReviewRepository;
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
public class ProfessorReviewController {

    private static final String SORT_LATEST = "latest";
    private static final String SORT_RATING_DESC = "rating_desc";

    private final ProfessorReviewRepository professorReviewRepository;
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;

    public ProfessorReviewController(
            ProfessorReviewRepository professorReviewRepository,
            ProfessorRepository professorRepository,
            UserRepository userRepository
    ) {
        this.professorReviewRepository = professorReviewRepository;
        this.professorRepository = professorRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/professors/{professorId}/reviews")
    ResponseEntity<Response<ReviewsResponseDTO>> getProfessorReviews(
            @PathVariable long professorId,
            @RequestParam(name = "sort", defaultValue = SORT_LATEST) String sort
    ) {
        if (!professorRepository.existsById(professorId)) {
            var msg = "El profesor con id " + professorId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        String normalizedSort = normalizeSort(sort);
        if (!isAllowedSort(normalizedSort)) {
            return invalidSortResponse();
        }

        List<ProfessorReview> reviews = SORT_RATING_DESC.equals(normalizedSort)
                ? professorReviewRepository.findByProfessorIdOrderByRatingDescCreatedAtDesc(professorId)
                : professorReviewRepository.findByProfessorIdOrderByCreatedAtDesc(professorId);

        var dto = new ReviewsResponseDTO(
                ReviewSummaryDTO.fromProjection(professorReviewRepository.summarizeByProfessorId(professorId)),
                ReviewItemDTO.fromProfessorReviews(reviews)
        );

        return ResponseEntity.ok(new Response<>(dto));
    }

    @PutMapping("/professors/{professorId}/reviews/me")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<Response<ReviewItemDTO>> upsertMyProfessorReview(
            @PathVariable long professorId,
            @Valid @RequestBody ReviewUpsertDTO body
    ) {
        var professorOpt = professorRepository.findById(professorId);
        if (professorOpt.isEmpty()) {
            var msg = "El profesor con id " + professorId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        var userOpt = currentUser();
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.notFound("Usuario autenticado no encontrado."));
        }

        var user = userOpt.get();
        var review = professorReviewRepository.findByUserIdAndProfessorId(user.getId(), professorId)
                .orElse(new ProfessorReview(user, professorOpt.get(), body.getRating(), body.getComment().trim()));

        review.setRating(body.getRating());
        review.setComment(body.getComment().trim());

        var saved = professorReviewRepository.save(review);
        return ResponseEntity.ok(new Response<>(ReviewItemDTO.fromProfessorReview(saved)));
    }

    @GetMapping("/professors/{professorId}/reviews/me")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<Response<ReviewItemDTO>> getMyProfessorReview(@PathVariable long professorId) {
        if (!professorRepository.existsById(professorId)) {
            var msg = "El profesor con id " + professorId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        var userOpt = currentUser();
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.notFound("Usuario autenticado no encontrado."));
        }

        var review = professorReviewRepository.findByUserIdAndProfessorId(userOpt.get().getId(), professorId);
        if (review.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.notFound("No existe review del usuario para el profesor indicado."));
        }

        return ResponseEntity.ok(new Response<>(ReviewItemDTO.fromProfessorReview(review.get())));
    }

    @DeleteMapping("/professors/{professorId}/reviews/me")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<Response<String>> deleteMyProfessorReview(@PathVariable long professorId) {
        if (!professorRepository.existsById(professorId)) {
            var msg = "El profesor con id " + professorId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        var userOpt = currentUser();
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.notFound("Usuario autenticado no encontrado."));
        }

        var reviewOpt = professorReviewRepository.findByUserIdAndProfessorId(userOpt.get().getId(), professorId);
        if (reviewOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.notFound("No existe review del usuario para el profesor indicado."));
        }

        professorReviewRepository.delete(reviewOpt.get());
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
