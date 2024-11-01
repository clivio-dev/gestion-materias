package Javastral.com.gestorMateriasWeb.web.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationData {
    private int page;
    private int pageSize;
    private long total;
} 