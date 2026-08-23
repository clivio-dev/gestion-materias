package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.entity.ProfessorReview;
import Javastral.com.gestorMateriasWeb.model.entity.SubjectReview;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewItemDTO(
        long id,
        int rating,
        String comment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ReviewItemDTO fromSubjectReview(SubjectReview review) {
        return new ReviewItemDTO(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }

    public static ReviewItemDTO fromProfessorReview(ProfessorReview review) {
        return new ReviewItemDTO(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }

    public static List<ReviewItemDTO> fromSubjectReviews(List<SubjectReview> reviews) {
        return reviews.stream()
                .map(ReviewItemDTO::fromSubjectReview)
                .toList();
    }

    public static List<ReviewItemDTO> fromProfessorReviews(List<ProfessorReview> reviews) {
        return reviews.stream()
                .map(ReviewItemDTO::fromProfessorReview)
                .toList();
    }
}
