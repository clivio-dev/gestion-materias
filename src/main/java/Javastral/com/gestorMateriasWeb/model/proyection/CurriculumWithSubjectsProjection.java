package Javastral.com.gestorMateriasWeb.model.proyection;

import java.util.Set;

public interface CurriculumWithSubjectsProjection {
    Long getId();
    String getName();
    Set<SubjectBasicProjection> getSubjects();
}