package Javastral.com.gestorMateriasWeb.web.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetaData {
    private PaginationData pagination;
} 