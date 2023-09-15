package Javastral.com.gestorMateriasWeb.web.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumDTO {
    private long id;
    private String name;
    private Set<SubjectDTO> subjects;
}
