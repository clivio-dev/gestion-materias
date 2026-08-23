package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.entity.Professor;
import Javastral.com.gestorMateriasWeb.model.entity.ProfessorReview;
import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class ProfessorReviewRepositoryIntegrationTest {

    @Autowired
    private ProfessorReviewRepository professorReviewRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void enforcesUniqueReviewPerUserAndProfessor() {
        Professor professor = professorRepository.save(new Professor("Juan Perez", null));
        UserEntity user = userRepository.save(new UserEntity("dave", "dave@example.com", "pass1234"));

        professorReviewRepository.saveAndFlush(new ProfessorReview(user, professor, 5, "Gran docente"));

        assertThatThrownBy(() -> professorReviewRepository.saveAndFlush(new ProfessorReview(user, professor, 3, "Ok")))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void summarizesAverageAndCountByProfessor() {
        Professor professor = professorRepository.save(new Professor("Ana Gomez", null));
        UserEntity user1 = userRepository.save(new UserEntity("erin", "erin@example.com", "pass1234"));
        UserEntity user2 = userRepository.save(new UserEntity("frank", "frank@example.com", "pass1234"));

        professorReviewRepository.saveAndFlush(new ProfessorReview(user1, professor, 5, "Excelente"));
        professorReviewRepository.saveAndFlush(new ProfessorReview(user2, professor, 1, "Malo"));

        var summary = professorReviewRepository.summarizeByProfessorId(professor.getId());

        assertThat(summary).isNotNull();
        assertThat(summary.getTotalReviews()).isEqualTo(2L);
        assertThat(summary.getAvgRating()).isEqualTo(3.0);
    }
}
