package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import Javastral.com.gestorMateriasWeb.web.controller.request.SubjectDTO;
import Javastral.com.gestorMateriasWeb.web.controller.response.Pagination;
import Javastral.com.gestorMateriasWeb.web.controller.response.Response;
import Javastral.com.gestorMateriasWeb.web.controller.response.Meta;
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
    ResponseEntity<Response<Set<SubjectDTO>>> getSubjectsByCurriculumId(@RequestParam String curriculumId) {
        var subjects = SubjectDTO.fromProjection(subjectRepository.findByCurriculumId(Long.parseLong(curriculumId)));
        var r = new Response<>(subjects);
        return ResponseEntity.ok(r);
    }

    @GetMapping("/all")
    ResponseEntity<Response<Set<SubjectDTO>>> getAllSubjects() {
        var subjects = SubjectDTO.fromProjection(subjectRepository.findAllBasicProy());
        var r = Response.fromCollection(subjects);
        return ResponseEntity.ok(r);
    }

//    @GetMapping("/passed")
//    ResponseEntity<ApiResponse<Set<String>>> getPassedSubjects() {
//        // TODO: completar
//
//    }

    @GetMapping("/{subjectId}/description")
    ResponseEntity<Response<String>> getSubjectDescription(@PathVariable String subjectId) {
        var description = subjectRepository.findDescriptionById(Long.parseLong(subjectId));
        if (description == null) {
            if(!subjectRepository.existsById(Long.parseLong(subjectId))) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound("La materia con id " + subjectId + " no existe."));
            } else {
                description = "No hay descripción disponible para esta materia.";
            }
        }
        var r = new Response<>(description);
        return ResponseEntity.ok(r);
    }
}
