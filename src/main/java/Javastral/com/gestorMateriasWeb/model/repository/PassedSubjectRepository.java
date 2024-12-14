package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.entity.PassedSubject;
import Javastral.com.gestorMateriasWeb.model.entity.UserSubjectId;
import Javastral.com.gestorMateriasWeb.model.proyection.PassedSubjectProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassedSubjectRepository extends JpaRepository<PassedSubject, UserSubjectId> {
    void deleteByUserId(Long userId);

    List<PassedSubjectProjection> findByUserId(long id);
}
