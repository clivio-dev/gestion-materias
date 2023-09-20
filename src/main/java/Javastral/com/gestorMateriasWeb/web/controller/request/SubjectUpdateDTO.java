package Javastral.com.gestorMateriasWeb.web.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectUpdateDTO {
    private Map<Long,Integer> save;
    private List<Long> delete;
}
