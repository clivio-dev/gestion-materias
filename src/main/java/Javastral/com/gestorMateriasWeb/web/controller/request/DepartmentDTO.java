package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import Javastral.com.gestorMateriasWeb.model.entity.Department;
import Javastral.com.gestorMateriasWeb.model.proyection.DepartmentBasicProy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private long id;
    private String name;
    private List<SimpleCurriculumDTO> curriculums;

    public static DepartmentDTO fromProjection(DepartmentBasicProy projection) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(projection.getId());
        dto.setName(projection.getName());
        dto.setCurriculums(projection.getCurriculumList().stream()
            .map(curr -> new SimpleCurriculumDTO(curr.getId(), curr.getName()))
            .collect(Collectors.toList()));
        return dto;
    }
}
