package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.entity.Subject;
import Javastral.com.gestorMateriasWeb.model.proyection.SubjectBasicProjection;
import jakarta.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s FROM Subject s JOIN s.curriculums c WHERE c.id = :curriculumId")
    Set<SubjectBasicProjection> findByCurriculumId(@Param("curriculumId") long curriculumId);

    @Query("SELECT s FROM Subject s")
    Set<SubjectBasicProjection> findAllBasicProy();

    @Query("SELECT s.description FROM Subject s WHERE s.id = :id")
    String findDescriptionById(long id);
}
