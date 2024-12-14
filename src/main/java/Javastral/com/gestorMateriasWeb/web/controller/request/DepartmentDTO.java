package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.proyection.DepartmentBasicProjection;
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

    public static DepartmentDTO fromProjection(DepartmentBasicProjection projection) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(projection.getId());
        dto.setName(projection.getName());
        dto.setCurriculums(projection.getCurriculumList().stream()
            .map(curr -> new SimpleCurriculumDTO(curr.getId(), curr.getName()))
            .collect(Collectors.toList()));
        return dto;
    }
}
