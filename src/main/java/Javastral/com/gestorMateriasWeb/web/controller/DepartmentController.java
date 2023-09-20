package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import Javastral.com.gestorMateriasWeb.model.entity.Department;
import Javastral.com.gestorMateriasWeb.model.repository.DepartmentRepository;
import Javastral.com.gestorMateriasWeb.web.controller.request.DepartmentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;


    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping()
    ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departmentDTOs = departmentRepository.findAll().stream()
                .map(DepartmentDTO::new).toList();
        return ResponseEntity.ok(departmentDTOs);
    }

    @GetMapping("/{departmentId}")
    ResponseEntity<List<Curriculum>> getCurriculumByDepartmentId(@PathVariable String departmentId) {
        Optional<Department> department = departmentRepository.findById(Long.parseLong(departmentId));
        return department.map(value -> ResponseEntity.ok(value.getCurriculumList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //TODO: mismo metodo pero q recibe una lista?
    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<String> saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        ResponseEntity<String> response;
        try{
            departmentRepository.save(new Department(departmentDTO.getId(),departmentDTO.getName()));
            response = ResponseEntity.ok("Department save.");
        } catch (Exception e){
            log.error(e.getMessage());

            response = departmentRepository.existsById(departmentDTO.getId())?
                    ResponseEntity.badRequest().body("Department with ID: " + departmentDTO.getId() + " already exists"):
                    ResponseEntity.internalServerError().build();
        }
        return response;
    }
}
