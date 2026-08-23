package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.Subject;
import Javastral.com.gestorMateriasWeb.model.entity.SubjectReview;
import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectReviewRepository;
import Javastral.com.gestorMateriasWeb.model.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SubjectReviewControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectReviewRepository subjectReviewRepository;

    private Subject subject;
    private UserEntity tester;

    @BeforeEach
    void setUp() {
        subject = buildSubject(2001L, "Ingenieria de Software");
        subject = subjectRepository.save(subject);

        tester = userRepository.save(new UserEntity("tester", "tester@example.com", "pass1234"));
    }

    @Test
    @WithMockUser(username = "tester")
    void putReviewMeCreatesAndThenUpdatesSingleReview() throws Exception {
        var createBody = objectMapper.writeValueAsString(new RequestBody(5, "Muy buena materia"));

        mockMvc.perform(put("/subjects/{subjectId}/reviews/me", subject.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.rating").value(5))
                .andExpect(jsonPath("$.data.comment").value("Muy buena materia"));

        var updateBody = objectMapper.writeValueAsString(new RequestBody(3, "Buena"));

        mockMvc.perform(put("/subjects/{subjectId}/reviews/me", subject.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.rating").value(3))
                .andExpect(jsonPath("$.data.comment").value("Buena"));

        var saved = subjectReviewRepository.findByUserIdAndSubjectId(tester.getId(), subject.getId());
        assertThat(saved).isPresent();
        assertThat(saved.get().getRating()).isEqualTo(3);
        assertThat(subjectReviewRepository.findBySubjectIdOrderByCreatedAtDesc(subject.getId())).hasSize(1);
    }

    @Test
    void getReviewMeRequiresAuthentication() throws Exception {
        mockMvc.perform(get("/subjects/{subjectId}/reviews/me", subject.getId()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "tester")
    void getReviewMeReturnsOnlyOwnReview() throws Exception {
        UserEntity other = userRepository.save(new UserEntity("other", "other@example.com", "pass1234"));

        subjectReviewRepository.save(new SubjectReview(tester, subject, 4, "Opinion tester"));
        subjectReviewRepository.save(new SubjectReview(other, subject, 1, "Opinion other"));

        mockMvc.perform(get("/subjects/{subjectId}/reviews/me", subject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.rating").value(4))
                .andExpect(jsonPath("$.data.comment").value("Opinion tester"));
    }

    @Test
    void getPublicReviewsDoesNotExposeAuthor() throws Exception {
        subjectReviewRepository.save(new SubjectReview(tester, subject, 5, "Excelente"));

        mockMvc.perform(get("/subjects/{subjectId}/reviews", subject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.summary.totalReviews").value(1))
                .andExpect(jsonPath("$.data.items[0].userId").doesNotExist())
                .andExpect(jsonPath("$.data.items[0].username").doesNotExist());
    }

    @Test
    @WithMockUser(username = "tester")
    void deleteReviewMeRemovesOwnReview() throws Exception {
        subjectReviewRepository.saveAndFlush(new SubjectReview(tester, subject, 5, "Excelente"));

        mockMvc.perform(delete("/subjects/{subjectId}/reviews/me", subject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Review eliminada correctamente."));

        assertThat(subjectReviewRepository.findByUserIdAndSubjectId(tester.getId(), subject.getId())).isEmpty();
    }

    private Subject buildSubject(long id, String name) {
        Subject s = new Subject();
        s.setId(id);
        s.setName(name);
        s.setPrerequisiteSubjects(new HashSet<>());
        s.setAnual(false);
        s.setSemester(1);
        return s;
    }

    private record RequestBody(int rating, String comment) {
    }
}
