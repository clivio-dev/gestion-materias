package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentDTO {
    private long id;
    private String name;

    public DepartmentDTO(Department department){
        this.id = department.getId();
        this.name = department.getName();
    }
}
