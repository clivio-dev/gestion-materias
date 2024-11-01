package Javastral.com.gestorMateriasWeb.model.proyection;

import java.util.List;

public interface DepartmentBasicProy {
    Long getId();
    String getName();
    List<CurriculumIdNameProy> getCurriculumList();
} 