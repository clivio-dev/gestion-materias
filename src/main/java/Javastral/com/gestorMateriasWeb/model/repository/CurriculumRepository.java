package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.Curriculum;
import Javastral.com.gestorMateriasWeb.model.proyection.CurriculumProjectionIdName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum,String> {

    @Query(value = "SELECT id,name FROM curriculums WHERE department_id = :department_id", nativeQuery = true)
    List<CurriculumProjectionIdName> getCurriculumByDepartmentIdProy(@Param("department_id")  String departmentId);

    @Query(value = "SELECT id,name FROM curriculums", nativeQuery = true)
    List<CurriculumProjectionIdName> getCurriculumProy();
}
