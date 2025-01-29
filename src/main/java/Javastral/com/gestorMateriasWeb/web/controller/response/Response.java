package Javastral.com.gestorMateriasWeb.web.controller.response;

import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Collections;

public record Response<T> (
        T data,
        Meta meta,
        Error errors
) {

    public Response(T data, Meta meta) {
        this(data, meta, null);
    }

    public Response(T data) {
        this(data, null, null);
    }

    public Response(Error errors) {
        this(null, null, errors);
    }

    public static <C extends Collection<E>, E> Response<C> fromCollection(C collection) {
        return new Response<>(collection, new Meta(Collections.singleton(collection.size())), null);
    }

    public static <T> Response<T> notFound(String message) {
        var e = new Error(message, HttpStatus.NOT_FOUND.toString());
        return new Response<>(e);
    }
}