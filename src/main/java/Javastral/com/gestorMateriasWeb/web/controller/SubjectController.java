package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import Javastral.com.gestorMateriasWeb.web.controller.request.SubjectDTO;
import Javastral.com.gestorMateriasWeb.web.controller.response.ApiResponse;
import Javastral.com.gestorMateriasWeb.web.controller.response.ErrorData;
import Javastral.com.gestorMateriasWeb.web.controller.response.MetaData;
import Javastral.com.gestorMateriasWeb.web.controller.response.PaginationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/subjects")
@CrossOrigin(origins = "*", maxAge = 3600) // TODO: para dev, aplicar configuracion de cors por db o env
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    // TODO: validar mejor los tipos de inputs
    @GetMapping
    ResponseEntity<ApiResponse<Set<SubjectDTO>>> getSubjectsByCurriculumId(@RequestParam String curriculumId) {
        Set<SubjectDTO> subjects = SubjectDTO.fromProjection(subjectRepository.findByCurriculumId(Long.parseLong(curriculumId)));
        return getApiResponseResponseEntity(subjects);
    }

    @GetMapping("all")
    ResponseEntity<ApiResponse<Set<SubjectDTO>>> getAllSubjects() {
        Set<SubjectDTO> subjects = SubjectDTO.fromProjection(subjectRepository.findAllBasicProy());
        return getApiResponseResponseEntity(subjects);
    }

    @GetMapping("/{subjectId}/description")
    ResponseEntity<ApiResponse<String>> getSubjectDescription(@PathVariable String subjectId) {
        String description = subjectRepository.findDescriptionById(Long.parseLong(subjectId));
        if (description == null) {
            if(!subjectRepository.existsById(Long.parseLong(subjectId))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<String>builder()
                        .data(null)
                        .meta(null)
                        .errors(ErrorData.builder()
                                .message("La materia con id " + subjectId + " no existe.")
                                .code("400")
                                .build())
                        .build());
            } else {
                description = "No hay descripción disponible para esta materia.";
            }
        }
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .data(description)
                .meta(null)
                .errors(null)
                .build());
    }

    private ResponseEntity<ApiResponse<Set<SubjectDTO>>> getApiResponseResponseEntity(Set<SubjectDTO> subjects) {
        ApiResponse<Set<SubjectDTO>> response = ApiResponse.<Set<SubjectDTO>>builder()
                .data(subjects)
                .meta(MetaData.builder()
                        .pagination(PaginationData.builder()
                                .page(1)
                                .pageSize(subjects.size())
                                .total(subjects.size())
                                .build())
                        .build())
                .errors(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
