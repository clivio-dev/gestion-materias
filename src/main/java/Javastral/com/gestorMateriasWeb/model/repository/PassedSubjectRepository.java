package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.PassedSubject;
import Javastral.com.gestorMateriasWeb.model.PassedSubjectKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassedSubjectRepository extends JpaRepository<PassedSubject, PassedSubjectKey> {
}
