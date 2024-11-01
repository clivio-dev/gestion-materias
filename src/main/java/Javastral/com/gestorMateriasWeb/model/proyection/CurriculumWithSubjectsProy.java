package Javastral.com.gestorMateriasWeb.model.proyection;

import java.util.Set;

public interface CurriculumWithSubjectsProy {
    Long getId();
    String getName();
    Set<SubjectBasicProy> getSubjects();
}