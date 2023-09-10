package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import Javastral.com.gestorMateriasWeb.model.proyection.CurriculumIdNameProy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum,String> {

    @Query(value = "SELECT id,name FROM curriculums", nativeQuery = true)
    List<CurriculumIdNameProy> getCurriculumProy();
}
