package Javastral.com.gestorMateriasWeb.web.controller;

import java.util.List;
import java.util.Set;

import Javastral.com.gestorMateriasWeb.model.entity.PassedSubject;
import Javastral.com.gestorMateriasWeb.model.entity.UserSubjectId;
import Javastral.com.gestorMateriasWeb.web.controller.request.PassedSubjectDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updatePassedSubjects(@RequestBody List<PassedSubjectDTO> passedSubjects) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            log.info("Updating passed subjects for user: {}", username);
            return userRepository.findByUsername(username)
                    .map(user -> {
                        try {
                            passedSubjectRepository.deleteByUserId(user.getId());

                            passedSubjects.forEach(subjectDTO -> subjectRepository.findById(subjectDTO.id())
                                    .ifPresent(subject -> {
                                        PassedSubject passedSubject = new PassedSubject();
                                        passedSubject.setId(new UserSubjectId(user.getId(), subject.getId()));
                                        passedSubject.setUser(user);
                                        passedSubject.setSubject(subject);
                                        passedSubject.setGrade(subjectDTO.grade());
                                        passedSubjectRepository.save(passedSubject);
                                    }));

                            return ResponseEntity.ok("Materias aprobadas actualizadas exitosamente");
                        } catch (Exception e) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body("Error actualizando materias aprobadas: " + e.getMessage());
                        }
                    })
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Usuario no encontrado"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }
    }

    @GetMapping("/passed-subjects")
    public ResponseEntity<List<PassedSubjectDTO>> getPassedSubjects() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();

            return userRepository.findByUsername(username)
                    .map(user -> {
                        List<PassedSubjectDTO> passedSubjects = PassedSubjectDTO.fromProjection(
                                passedSubjectRepository.findByUserId(user.getId())
                        );
                        return ResponseEntity.ok(passedSubjects);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

