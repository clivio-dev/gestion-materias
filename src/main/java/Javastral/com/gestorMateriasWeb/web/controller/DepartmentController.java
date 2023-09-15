package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import Javastral.com.gestorMateriasWeb.model.entity.Department;
import Javastral.com.gestorMateriasWeb.model.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;


    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/all")
    ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @GetMapping("/{departmentId}/curriculum")
    ResponseEntity<List<Curriculum>> getCurriculumByDepartmentId(@PathVariable String departmentId) {
        Optional<Department> department = departmentRepository.findById(Long.parseLong(departmentId));
        return department.map(value -> ResponseEntity.ok(value.getCurriculumList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
