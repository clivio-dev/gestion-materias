package Javastral.com.gestorMateriasWeb.model.proyection;

import java.util.Set;

public interface SubjectBasicProy {
    Long getId();
    String getName();
    Set<Long> getPrerequisiteSubjects();
}
