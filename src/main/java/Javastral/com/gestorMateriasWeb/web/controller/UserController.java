package Javastral.com.gestorMateriasWeb.web.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import Javastral.com.gestorMateriasWeb.model.entity.PassedSubject;
import Javastral.com.gestorMateriasWeb.model.entity.Subject;
import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;
import Javastral.com.gestorMateriasWeb.model.entity.UserSubjectId;
import Javastral.com.gestorMateriasWeb.web.controller.request.PassedSubjectDTO;
import Javastral.com.gestorMateriasWeb.web.controller.response.Response;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import Javastral.com.gestorMateriasWeb.model.repository.UserRepository;
import Javastral.com.gestorMateriasWeb.model.repository.PassedSubjectRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")

@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final PassedSubjectRepository passedSubjectRepository;

    @Autowired
    public UserController(UserRepository userRepository, SubjectRepository subjectRepository, PassedSubjectRepository passedSubjectRepository) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.passedSubjectRepository = passedSubjectRepository;
    }

    @PostMapping("/update-passed")
    @PreAuthorize("isAuthenticated()")
    @Transactional
    public ResponseEntity<Response<String>> updatePassedSubjects(
            @Valid @RequestBody List<PassedSubjectDTO> passedSubjects) {
        var user = getUser();
        log.info("Iniciando actualización de materias aprobadas para usuario: {}", user.getUsername());

        try {
            passedSubjectRepository.deleteByUserId(user.getId());

            var subjectIds = passedSubjects.stream()
                    .map(PassedSubjectDTO::id)
                    .collect(Collectors.toSet());

            Map<Long, Subject> subjectsMap = subjectRepository.findAllById(subjectIds)
                    .stream()
                    .collect(Collectors.toMap(Subject::getId, Function.identity()));

            var invalidIds = subjectIds.stream()
                    .filter(id -> !subjectsMap.containsKey(id))
                    .toList();

            if (!invalidIds.isEmpty()) {
                log.warn("Materias no encontradas: {}", invalidIds);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Materias no válidas: " + invalidIds);
            }

            var newSubjects = passedSubjects.stream()
                    .map(dto -> new PassedSubject(
                            new UserSubjectId(user.getId(), dto.id()),
                            dto.grade(),
                            user,
                            subjectsMap.get(dto.id())
                    ))
                    .toList();

            passedSubjectRepository.saveAll(newSubjects);

            log.info("Actualización exitosa para usuario: {}", user.getUsername());
            return ResponseEntity.ok(new Response<>("Actualización exitosa"));

        } catch (ResponseStatusException e) {
            throw e; // esto ya lo controla spring asi que se deja pasar
        } catch (Exception e) {
            log.error("Error crítico actualizando materias para {}: {}", user.getUsername(), e.getMessage(), e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error interno procesando la solicitud"
            );
        }
    }

    @GetMapping("/passed-subjects")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<List<PassedSubjectDTO>>> getPassedSubjects() {
        var data = PassedSubjectDTO.fromProjection(
                passedSubjectRepository.findByUserId(getUser().getId())
        );

        return ResponseEntity.ok(Response.fromCollection(data));
    }

    UserEntity getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var opt = userRepository.findByUsername(username);
        if (opt.isEmpty()) {
            log.warn("Usuario autenticado no encontrado en BD: {}", username);
            // esto no es un response de javastral por que realmente es mas un error de inconsistencia de la db que un error de la request
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        return opt.get();
    }
}

