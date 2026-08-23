package Javastral.com.gestorMateriasWeb.web.controller.request;

import java.util.List;

public record ReviewsResponseDTO(
        ReviewSummaryDTO summary,
        List<ReviewItemDTO> items
) {
}
