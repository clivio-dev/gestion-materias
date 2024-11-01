package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.entity.Subject;
import Javastral.com.gestorMateriasWeb.model.proyection.SubjectBasicProy;
import Javastral.com.gestorMateriasWeb.web.controller.request.SubjectDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s FROM Subject s JOIN s.curriculums c WHERE c.id = :curriculumId")
    Set<SubjectBasicProy> findByCurriculumId(@Param("curriculumId") long curriculumId);

    @Query("SELECT s FROM Subject s")
    Set<SubjectBasicProy> findAllBasicProy();
}
