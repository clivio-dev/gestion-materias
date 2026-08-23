package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.SubjectProfessor;
import Javastral.com.gestorMateriasWeb.model.entity.SubjectProfessorId;
import Javastral.com.gestorMateriasWeb.model.repository.ProfessorRepository;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectProfessorRepository;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import Javastral.com.gestorMateriasWeb.web.controller.request.ProfessorDTO;
import Javastral.com.gestorMateriasWeb.web.controller.response.Error;
import Javastral.com.gestorMateriasWeb.web.controller.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfessorController {

    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectProfessorRepository subjectProfessorRepository;

    public ProfessorController(
            ProfessorRepository professorRepository,
            SubjectRepository subjectRepository,
            SubjectProfessorRepository subjectProfessorRepository
    ) {
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.subjectProfessorRepository = subjectProfessorRepository;
    }

    @GetMapping("/professors")
    ResponseEntity<Response<List<ProfessorDTO>>> getProfessors(
            @RequestParam(name = "q", required = false) String query
    ) {
        var projections = query == null || query.isBlank()
                ? professorRepository.findAllBasicProjection()
                : professorRepository.searchBasicProjection(query.trim());

        var professors = ProfessorDTO.fromProjection(projections);
        return ResponseEntity.ok(Response.fromCollection(professors));
    }

    @GetMapping("/subjects/{subjectId}/professors")
    ResponseEntity<Response<List<ProfessorDTO>>> getProfessorsBySubjectId(@PathVariable long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            var msg = "La materia con id " + subjectId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        var professors = ProfessorDTO.fromProjection(professorRepository.findBySubjectIdBasicProjection(subjectId));
        return ResponseEntity.ok(Response.fromCollection(professors));
    }

    @PostMapping("/subjects/{subjectId}/professors/{professorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MOD')")
    ResponseEntity<Response<String>> addProfessorToSubject(
            @PathVariable long subjectId,
            @PathVariable long professorId
    ) {
        var subjectOpt = subjectRepository.findById(subjectId);
        if (subjectOpt.isEmpty()) {
            var msg = "La materia con id " + subjectId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        var professorOpt = professorRepository.findById(professorId);
        if (professorOpt.isEmpty()) {
            var msg = "El profesor con id " + professorId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        var relationId = new SubjectProfessorId(subjectId, professorId);
        if (subjectProfessorRepository.existsById(relationId)) {
            var error = new Error("La relacion materia-profesor ya existe.", HttpStatus.CONFLICT.toString());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response<>(error));
        }

        subjectProfessorRepository.save(new SubjectProfessor(subjectOpt.get(), professorOpt.get()));
        return ResponseEntity.ok(new Response<>("Profesor asociado correctamente a la materia."));
    }

    @DeleteMapping("/subjects/{subjectId}/professors/{professorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MOD')")
    ResponseEntity<Response<String>> removeProfessorFromSubject(
            @PathVariable long subjectId,
            @PathVariable long professorId
    ) {
        var relationId = new SubjectProfessorId(subjectId, professorId);
        if (!subjectProfessorRepository.existsById(relationId)) {
            var msg = "La relacion materia-profesor no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.notFound(msg));
        }

        subjectProfessorRepository.deleteById(relationId);
        return ResponseEntity.ok(new Response<>("Profesor desasociado correctamente de la materia."));
    }
}
