package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.entity.Subject;
import Javastral.com.gestorMateriasWeb.model.entity.SubjectReview;
import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class SubjectReviewRepositoryIntegrationTest {

    @Autowired
    private SubjectReviewRepository subjectReviewRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void enforcesUniqueReviewPerUserAndSubject() {
        Subject subject = buildSubject(1001L, "Programacion 1");
        subjectRepository.save(subject);

        UserEntity user = userRepository.save(new UserEntity("alice", "alice@example.com", "pass1234"));

        subjectReviewRepository.saveAndFlush(new SubjectReview(user, subject, 5, "Excelente"));

        assertThatThrownBy(() -> subjectReviewRepository.saveAndFlush(new SubjectReview(user, subject, 4, "Muy buena")))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void summarizesAverageAndCountBySubject() {
        Subject subject = buildSubject(1002L, "Analisis");
        subjectRepository.save(subject);

        UserEntity user1 = userRepository.save(new UserEntity("bob", "bob@example.com", "pass1234"));
        UserEntity user2 = userRepository.save(new UserEntity("carol", "carol@example.com", "pass1234"));

        subjectReviewRepository.saveAndFlush(new SubjectReview(user1, subject, 4, "Buena"));
        subjectReviewRepository.saveAndFlush(new SubjectReview(user2, subject, 2, "Regular"));

        var summary = subjectReviewRepository.summarizeBySubjectId(subject.getId());

        assertThat(summary).isNotNull();
        assertThat(summary.getTotalReviews()).isEqualTo(2L);
        assertThat(summary.getAvgRating()).isEqualTo(3.0);
    }

    private Subject buildSubject(long id, String name) {
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName(name);
        subject.setPrerequisiteSubjects(new HashSet<>());
        subject.setAnual(false);
        subject.setSemester(1);
        return subject;
    }
}
