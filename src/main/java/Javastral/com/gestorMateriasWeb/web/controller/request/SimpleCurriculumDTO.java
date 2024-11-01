package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleCurriculumDTO {
    private String id;
    private String name;
}