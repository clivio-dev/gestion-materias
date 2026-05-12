package Javastral.com.gestorMateriasWeb.web.controller.response;

public record Error(
        String message,
        String code
) {
    public static Error empty() {
        return new Error("", "");
    }
}