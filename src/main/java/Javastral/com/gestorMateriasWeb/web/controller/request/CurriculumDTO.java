package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.proyection.CurriculumWithSubjectsProy;
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

    public static CurriculumDTO fromProjection(CurriculumWithSubjectsProy curriculumWithSubjectsProy) {
        return new CurriculumDTO(
            curriculumWithSubjectsProy.getId(),
            curriculumWithSubjectsProy.getName(),
            SubjectDTO.fromProjection(curriculumWithSubjectsProy.getSubjects())
        );
    }
}
