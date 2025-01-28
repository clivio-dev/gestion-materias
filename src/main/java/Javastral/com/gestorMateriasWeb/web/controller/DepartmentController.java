package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import Javastral.com.gestorMateriasWeb.model.entity.Department;
import Javastral.com.gestorMateriasWeb.model.proyection.DepartmentBasicProjection;
import Javastral.com.gestorMateriasWeb.model.repository.DepartmentRepository;
import Javastral.com.gestorMateriasWeb.web.controller.request.DepartmentDTO;
import Javastral.com.gestorMateriasWeb.web.controller.response.Response;
import Javastral.com.gestorMateriasWeb.web.controller.response.Error;
import Javastral.com.gestorMateriasWeb.web.controller.response.Meta;
import Javastral.com.gestorMateriasWeb.web.controller.response.PaginationData;
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
        List<DepartmentBasicProjection> departments = departmentRepository.findAllDepartmentsWithBasicCurriculums();
        List<DepartmentDTO> departmentDTOs = departments.stream()
            .map(DepartmentDTO::fromProjection)
            .collect(Collectors.toList());

        Response<List<DepartmentDTO>> response = Response.<List<DepartmentDTO>>builder()
            .data(departmentDTOs)
            .meta(Meta.builder()
                .pagination(PaginationData.builder()
                    .page(1)
                    .pageSize(departmentDTOs.size())
                    .total(departmentDTOs.size())
                    .build())
                .build())
            .errors(null)
            .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{departmentId}")
    @Transactional
    ResponseEntity<Response<List<Curriculum>>> getCurriculumByDepartmentId(@PathVariable String departmentId) {
        Optional<Department> department = departmentRepository.findById(Long.parseLong(departmentId));
        
        if (department.isPresent()) {
            Response<List<Curriculum>> response = Response.<List<Curriculum>>builder()
                .data(department.get().getCurriculumList())
                .meta(Meta.builder()
                    .pagination(PaginationData.builder()
                        .page(1)
                        .pageSize(department.get().getCurriculumList().size())
                        .total(department.get().getCurriculumList().size())
                        .build())
                    .build())
                .errors(null)
                .build();
            return ResponseEntity.ok(response);
        }

        Response<List<Curriculum>> errorResponse = Response.<List<Curriculum>>builder()
            .data(null)
            .meta(null)
            .errors(Error.builder()
                .message("Department not found")
                .code("404")
                .build())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Response<String>> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            departmentRepository.save(new Department(departmentDTO.getId(), departmentDTO.getName()));
            
            Response<String> response = Response.<String>builder()
                .data("Department saved")
                .meta(null)
                .errors(null)
                .build();
                
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            
            String errorMessage = departmentRepository.existsById(departmentDTO.getId()) ?
                "Department with ID: " + departmentDTO.getId() + " already exists" :
                "Internal server error";
                
            Response<String> errorResponse = Response.<String>builder()
                .data(null)
                .meta(null)
                .errors(Error.builder()
                    .message(errorMessage)
                    .code(departmentRepository.existsById(departmentDTO.getId()) ? "400" : "500")
                    .build())
                .build();
                
            return ResponseEntity
                .status(departmentRepository.existsById(departmentDTO.getId()) ? 
                    HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
        }
    }
}
