package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.Professor;
import Javastral.com.gestorMateriasWeb.model.entity.ProfessorReview;
import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;
import Javastral.com.gestorMateriasWeb.model.repository.ProfessorRepository;
import Javastral.com.gestorMateriasWeb.model.repository.ProfessorReviewRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProfessorReviewControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfessorReviewRepository professorReviewRepository;

    private Professor professor;
    private UserEntity tester;

    @BeforeEach
    void setUp() {
        professor = professorRepository.save(new Professor("Maria Lopez", null));
        tester = userRepository.save(new UserEntity("tester_prof", "tester_prof@example.com", "pass1234"));
    }

    @Test
    @WithMockUser(username = "tester_prof")
    void putAndGetMyProfessorReview() throws Exception {
        var body = objectMapper.writeValueAsString(new RequestBody(5, "Muy clara"));

        mockMvc.perform(put("/professors/{professorId}/reviews/me", professor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.rating").value(5))
                .andExpect(jsonPath("$.data.comment").value("Muy clara"));

        mockMvc.perform(get("/professors/{professorId}/reviews/me", professor.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.rating").value(5))
                .andExpect(jsonPath("$.data.comment").value("Muy clara"));

        assertThat(professorReviewRepository.findByUserIdAndProfessorId(tester.getId(), professor.getId())).isPresent();
    }

    @Test
    void getPublicProfessorReviewsDoesNotExposeAuthor() throws Exception {
        professorReviewRepository.save(new ProfessorReview(tester, professor, 4, "Buena"));

        mockMvc.perform(get("/professors/{professorId}/reviews", professor.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.summary.totalReviews").value(1))
                .andExpect(jsonPath("$.data.items[0].userId").doesNotExist())
                .andExpect(jsonPath("$.data.items[0].username").doesNotExist());
    }

    @Test
    @WithMockUser(username = "tester_prof")
    void deleteProfessorReviewMeRemovesOwnReview() throws Exception {
        professorReviewRepository.saveAndFlush(new ProfessorReview(tester, professor, 4, "Buena"));

        mockMvc.perform(delete("/professors/{professorId}/reviews/me", professor.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Review eliminada correctamente."));

        assertThat(professorReviewRepository.findByUserIdAndProfessorId(tester.getId(), professor.getId())).isEmpty();
    }

    private record RequestBody(int rating, String comment) {
    }
}
