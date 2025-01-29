package Javastral.com.gestorMateriasWeb.web.controller.response;

import java.util.Collection;

public record Pagination(
        int page,
        int pageSize,
        int total
) {
    public static Pagination empty() {
        return new Pagination(0, 0, 0);
    }

    public Pagination(Collection<?> collection) {
        this(1, collection.size(), collection.size());
    }
}