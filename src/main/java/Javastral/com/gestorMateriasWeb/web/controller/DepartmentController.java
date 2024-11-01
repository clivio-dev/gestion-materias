package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import Javastral.com.gestorMateriasWeb.model.entity.Department;
import Javastral.com.gestorMateriasWeb.model.proyection.DepartmentBasicProy;
import Javastral.com.gestorMateriasWeb.model.repository.DepartmentRepository;
import Javastral.com.gestorMateriasWeb.web.controller.request.DepartmentDTO;
import Javastral.com.gestorMateriasWeb.web.controller.response.ApiResponse;
import Javastral.com.gestorMateriasWeb.web.controller.response.ErrorData;
import Javastral.com.gestorMateriasWeb.web.controller.response.MetaData;
import Javastral.com.gestorMateriasWeb.web.controller.response.PaginationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    ResponseEntity<ApiResponse<List<DepartmentDTO>>> getAllDepartments() {
        List<DepartmentBasicProy> departments = departmentRepository.findAllDepartmentsWithBasicCurriculums();
        List<DepartmentDTO> departmentDTOs = departments.stream()
            .map(DepartmentDTO::fromProjection)
            .collect(Collectors.toList());

        ApiResponse<List<DepartmentDTO>> response = ApiResponse.<List<DepartmentDTO>>builder()
            .data(departmentDTOs)
            .meta(MetaData.builder()
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
    ResponseEntity<ApiResponse<List<Curriculum>>> getCurriculumByDepartmentId(@PathVariable String departmentId) {
        Optional<Department> department = departmentRepository.findById(Long.parseLong(departmentId));
        
        if (department.isPresent()) {
            ApiResponse<List<Curriculum>> response = ApiResponse.<List<Curriculum>>builder()
                .data(department.get().getCurriculumList())
                .meta(MetaData.builder()
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

        ApiResponse<List<Curriculum>> errorResponse = ApiResponse.<List<Curriculum>>builder()
            .data(null)
            .meta(null)
            .errors(ErrorData.builder()
                .message("Department not found")
                .code("404")
                .build())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ApiResponse<String>> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            departmentRepository.save(new Department(departmentDTO.getId(), departmentDTO.getName()));
            
            ApiResponse<String> response = ApiResponse.<String>builder()
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
                
            ApiResponse<String> errorResponse = ApiResponse.<String>builder()
                .data(null)
                .meta(null)
                .errors(ErrorData.builder()
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
