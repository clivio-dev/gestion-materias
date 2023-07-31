package Javastral.com.gestorMateriasWeb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository <Subject,String> {

    @Query(value = "SELECT subject_id AS subjectId,score FROM passed_subjects WHERE user_id = :id", nativeQuery = true)
    Set<PassedSubjectScoreProjection> getPassedSubjectsScoreByUserId(@Param("id") String id);

    @Query(value = "SELECT s.* FROM subjects AS s JOIN curriculums_subjects AS cs ON cs.subject_id = s.id WHERE curriculum_id = :id", nativeQuery = true)
    Set<Subject> getCurriculumById(@Param("id") String id);
}
