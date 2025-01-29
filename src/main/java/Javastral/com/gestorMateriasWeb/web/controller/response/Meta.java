package Javastral.com.gestorMateriasWeb.web.controller.response;

import java.util.Collection;

public record Meta(
        Pagination pagination
) {
    public static Meta empty() {
        return new Meta(Pagination.empty());
    }

    public Meta(Collection<?> collection) {
        this(new Pagination(collection));
    }
}