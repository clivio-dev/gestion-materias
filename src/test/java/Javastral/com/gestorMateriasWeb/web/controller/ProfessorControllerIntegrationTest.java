package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.Professor;
import Javastral.com.gestorMateriasWeb.model.entity.Subject;
import Javastral.com.gestorMateriasWeb.model.entity.SubjectProfessor;
import Javastral.com.gestorMateriasWeb.model.entity.SubjectProfessorId;
import Javastral.com.gestorMateriasWeb.model.repository.ProfessorRepository;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectProfessorRepository;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProfessorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectProfessorRepository subjectProfessorRepository;

    private Subject subject;
    private Professor professor;

    @BeforeEach
    void setUp() {
        subject = new Subject();
        subject.setId(3001L);
        subject.setName("Sistemas Operativos");
        subject.setPrerequisiteSubjects(new HashSet<>());
        subject.setAnual(false);
        subject.setSemester(2);
        subject = subjectRepository.save(subject);

        professor = professorRepository.save(new Professor("Carlos Diaz", null));
        subjectProfessorRepository.save(new SubjectProfessor(subject, professor));
    }

    @Test
    void getProfessorsReturnsList() throws Exception {
        mockMvc.perform(get("/professors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].fullName").value("Carlos Diaz"));
    }

    @Test
    void getSubjectProfessorsReturnsAssociatedProfessors() throws Exception {
        mockMvc.perform(get("/subjects/{subjectId}/professors", subject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].fullName").value("Carlos Diaz"));
    }

    @Test
    @WithMockUser(username = "adminUser", authorities = {"ADMIN"})
    void adminCanAssociateAndRemoveProfessorFromSubject() throws Exception {
        Professor secondProfessor = professorRepository.save(new Professor("Laura Velez", null));

        mockMvc.perform(post("/subjects/{subjectId}/professors/{professorId}", subject.getId(), secondProfessor.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Profesor asociado correctamente a la materia."));

        assertThat(subjectProfessorRepository.existsById(new SubjectProfessorId(subject.getId(), secondProfessor.getId())))
                .isTrue();

        mockMvc.perform(delete("/subjects/{subjectId}/professors/{professorId}", subject.getId(), secondProfessor.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Profesor desasociado correctamente de la materia."));

        assertThat(subjectProfessorRepository.existsById(new SubjectProfessorId(subject.getId(), secondProfessor.getId())))
                .isFalse();
    }

    @Test
    @WithMockUser(username = "regularUser", authorities = {"USER"})
    void nonAdminCannotAssociateProfessorToSubject() throws Exception {
        mockMvc.perform(post("/subjects/{subjectId}/professors/{professorId}", subject.getId(), professor.getId()))
                .andExpect(status().isForbidden());
    }
}
