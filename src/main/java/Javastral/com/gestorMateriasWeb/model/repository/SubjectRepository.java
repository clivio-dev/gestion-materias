package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.proyection.PassedSubjectProyectionIdScore;
import Javastral.com.gestorMateriasWeb.model.Subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {

    @Query(value = "SELECT subject_id AS subjectId,score FROM passed_subjects WHERE user_id = :id", nativeQuery = true)
    Set<PassedSubjectProyectionIdScore> getPassedSubjectsScoreByUserId(@Param("id") String id);

}
