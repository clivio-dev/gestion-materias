package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.proyection.SubjectBasicProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private long id;
    private String name;
    private int semester;
    private boolean anual;
    private Set<Long> prerequisites;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectDTO that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static SubjectDTO fromProjection(SubjectBasicProjection subjectBasicProjection) {
        return new SubjectDTO(
            subjectBasicProjection.getId(),
            subjectBasicProjection.getName(),
            subjectBasicProjection.getSemester(),
            subjectBasicProjection.getAnual(),
            subjectBasicProjection.getPrerequisiteSubjects()
        );
    }

    public static Set<SubjectDTO> fromProjection(Set<SubjectBasicProjection> subjects) {
        return subjects.stream()
                .map(SubjectDTO::fromProjection).collect(Collectors.toSet());
    }
}
