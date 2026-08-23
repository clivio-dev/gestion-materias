package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.entity.Professor;
import Javastral.com.gestorMateriasWeb.model.proyection.ProfessorBasicProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("""
            SELECT p.id as id,
                   p.fullName as fullName,
                   d.id as departmentId,
                   d.name as departmentName
            FROM Professor p
            LEFT JOIN p.department d
            ORDER BY p.fullName ASC
            """)
    List<ProfessorBasicProjection> findAllBasicProjection();

    @Query("""
            SELECT p.id as id,
                   p.fullName as fullName,
                   d.id as departmentId,
                   d.name as departmentName
            FROM Professor p
            LEFT JOIN p.department d
            WHERE LOWER(p.fullName) LIKE LOWER(CONCAT('%', :query, '%'))
            ORDER BY p.fullName ASC
            """)
    List<ProfessorBasicProjection> searchBasicProjection(@Param("query") String query);

    @Query("""
            SELECT p.id as id,
                   p.fullName as fullName,
                   d.id as departmentId,
                   d.name as departmentName
            FROM SubjectProfessor sp
            JOIN sp.professor p
            LEFT JOIN p.department d
            WHERE sp.subject.id = :subjectId
            ORDER BY p.fullName ASC
            """)
    List<ProfessorBasicProjection> findBySubjectIdBasicProjection(@Param("subjectId") long subjectId);
}
