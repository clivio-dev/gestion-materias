package Javastral.com.gestorMateriasWeb.web.controller.response;

public record Pagination(
        int page,
        int pageSize,
        long total
) {
    public static Pagination empty() {
        return new Pagination(0, 0, 0);
    }
}