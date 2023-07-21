package Javastral.com.gestorMateriasWeb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository <PassedSubject,PassedSubjectKey> {

    @Query(value = "SELECT subject_id as subjectId,score FROM passed_subjects WHERE user_id = :id", nativeQuery = true)
    Set<PassedSubjectScoreProjection> getPassedSubjectsScoreByUserId(@Param("id") String id);
}
