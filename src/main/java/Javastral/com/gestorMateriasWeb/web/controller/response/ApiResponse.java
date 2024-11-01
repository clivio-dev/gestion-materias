package Javastral.com.gestorMateriasWeb.web.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    private T data;
    private MetaData meta;
    private ErrorData errors;
} 