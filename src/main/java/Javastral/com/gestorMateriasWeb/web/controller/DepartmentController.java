package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import Javastral.com.gestorMateriasWeb.model.entity.Department;
import Javastral.com.gestorMateriasWeb.model.proyection.DepartmentBasicProjection;
import Javastral.com.gestorMateriasWeb.model.repository.DepartmentRepository;
import Javastral.com.gestorMateriasWeb.web.controller.request.DepartmentDTO;
import Javastral.com.gestorMateriasWeb.web.controller.response.Pagination;
import Javastral.com.gestorMateriasWeb.web.controller.response.Response;
import Javastral.com.gestorMateriasWeb.web.controller.response.Error;
import Javastral.com.gestorMateriasWeb.web.controller.response.Meta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/departments")
@CrossOrigin(origins = "*", maxAge = 3600) // TODO: para dev, aplicar configuracion de cors por db o env
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping()
    ResponseEntity<Response<List<DepartmentDTO>>> getAllDepartments() {
        var departmentDTOs = departmentRepository.findAllDepartmentsWithBasicCurriculums().stream()
            .map(DepartmentDTO::fromProjection)
            .collect(Collectors.toList());

        var r = Response.fromCollection(departmentDTOs);
        return ResponseEntity.ok(r);
    }

    @GetMapping("/{departmentId}")
    @Transactional
    ResponseEntity<Response<List<Curriculum>>> getCurriculumByDepartmentId(@PathVariable String departmentId) {
        var department = departmentRepository.findById(Long.parseLong(departmentId));
        
        if (department.isEmpty()) {
            var msg = "Department with id " + departmentId + "not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        var curriculums = department.get().getCurriculumList();
        var r = Response.fromCollection(curriculums);
        return ResponseEntity.ok(r);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Response<DepartmentDTO>> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            departmentRepository.save(new Department(departmentDTO.getId(), departmentDTO.getName()));
            var r = new Response<>(departmentDTO);
            return ResponseEntity.ok(r);
        } catch (Exception e) {
            log.error("Error al guardar departamento", e);

            boolean exists = departmentRepository.existsById(departmentDTO.getId());
            var msg = exists
                    ? "Department with ID " + departmentDTO.getId() + " already exists"
                    : "Internal server error";
            var code = exists ? HttpStatus.CONFLICT.toString() : HttpStatus.INTERNAL_SERVER_ERROR.toString();
            var status = exists ? HttpStatus.CONFLICT : HttpStatus.INTERNAL_SERVER_ERROR;
            var error = new Error(msg, code);

            Response<DepartmentDTO> r = new Response<>(error);
            return ResponseEntity.status(status).body(r);
        }
    }
}
