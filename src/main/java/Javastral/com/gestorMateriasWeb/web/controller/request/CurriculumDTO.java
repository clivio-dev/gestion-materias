package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.proyection.CurriculumWithSubjectsProjection;
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

    public static CurriculumDTO fromProjection(CurriculumWithSubjectsProjection curriculumWithSubjectsProjection) {
        return new CurriculumDTO(
            curriculumWithSubjectsProjection.getId(),
            curriculumWithSubjectsProjection.getName(),
            SubjectDTO.fromProjection(curriculumWithSubjectsProjection.getSubjects())
        );
    }
}
