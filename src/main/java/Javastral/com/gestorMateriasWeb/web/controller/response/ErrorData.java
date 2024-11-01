package Javastral.com.gestorMateriasWeb.web.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorData {
    private String message;
    private String code;
} 