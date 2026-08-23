package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.proyection.ReviewSummaryProjection;

public record ReviewSummaryDTO(
        double avgRating,
        long totalReviews
) {
    public static ReviewSummaryDTO fromProjection(ReviewSummaryProjection projection) {
        if (projection == null) {
            return new ReviewSummaryDTO(0.0, 0);
        }

        double avg = projection.getAvgRating() == null ? 0.0 : projection.getAvgRating();
        long total = projection.getTotalReviews() == null ? 0 : projection.getTotalReviews();

        return new ReviewSummaryDTO(avg, total);
    }
}
