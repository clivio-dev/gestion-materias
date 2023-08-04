package Javastral.com.gestorMateriasWeb.web;

import Javastral.com.gestorMateriasWeb.model.Curriculum;
import Javastral.com.gestorMateriasWeb.model.Department;
import Javastral.com.gestorMateriasWeb.model.proyection.CurriculumProyectionIdName;
import Javastral.com.gestorMateriasWeb.model.repository.CurriculumRepository;
import Javastral.com.gestorMateriasWeb.model.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final CurriculumRepository curriculumRepository;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository, CurriculumRepository curriculumRepository) {
        this.departmentRepository = departmentRepository;
        this.curriculumRepository = curriculumRepository;
    }

    @GetMapping("/all")
    ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @GetMapping("/{departmentId}/curriculum")
    ResponseEntity<List<CurriculumProyectionIdName>> getCurriculumByDepartmentId(@PathVariable String departmentId) {
        if(departmentRepository.existsById(departmentId)){
            return ResponseEntity.ok(curriculumRepository.getCurriculumByDepartmentIdProy(departmentId));
        }
        return ResponseEntity.notFound().build();
    }
}
