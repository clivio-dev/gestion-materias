package Javastral.com.gestorMateriasWeb.model.proyection;

import java.util.Set;

public interface SubjectBasicProjection {
    Long getId();
    String getName();
    int getSemester();
    boolean getAnual();
    Set<Long> getPrerequisiteSubjects();
}
