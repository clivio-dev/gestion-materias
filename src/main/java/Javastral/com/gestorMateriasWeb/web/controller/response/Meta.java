package Javastral.com.gestorMateriasWeb.web.controller.response;

public record Meta(
        Pagination pagination
) {
    public static Meta empty() {
        return new Meta(Pagination.empty());
    }
}