package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import Javastral.com.gestorMateriasWeb.model.proyection.CurriculumIdNameProjection;
import Javastral.com.gestorMateriasWeb.model.proyection.CurriculumWithSubjectsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum,Long> {

    @Query("SELECT c FROM Curriculum c LEFT JOIN FETCH c.subjects WHERE c.id = :curriculumId")
    Optional<CurriculumWithSubjectsProjection> findCurriculumWithSubjectsById(@Param("curriculumId") Long curriculumId);

    @Query("SELECT c.id as id, c.name as name FROM Curriculum c")
    List<CurriculumIdNameProjection> getCurriculumProy();
}
