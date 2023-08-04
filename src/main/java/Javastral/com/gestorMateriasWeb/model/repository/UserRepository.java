package Javastral.com.gestorMateriasWeb.model.repository;

import Javastral.com.gestorMateriasWeb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

