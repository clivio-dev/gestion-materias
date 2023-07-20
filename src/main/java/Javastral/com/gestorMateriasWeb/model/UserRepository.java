package Javastral.com.gestorMateriasWeb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT subject_id FROM passed_subjects WHERE user_id = :id", nativeQuery = true)
    Set<Integer> getPassedSubjects(@Param("id") String id);
}

