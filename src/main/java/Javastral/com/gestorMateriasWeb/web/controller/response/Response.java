package Javastral.com.gestorMateriasWeb.web.controller.response;

public record Response<T> (
        T data,
        Meta meta,
        Error errors
) {}