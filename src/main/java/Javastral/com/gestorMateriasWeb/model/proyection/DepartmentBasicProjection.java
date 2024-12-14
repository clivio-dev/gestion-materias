package Javastral.com.gestorMateriasWeb.model.proyection;

import java.util.List;

public interface DepartmentBasicProjection {
    Long getId();
    String getName();
    List<CurriculumIdNameProjection> getCurriculumList();
} 
