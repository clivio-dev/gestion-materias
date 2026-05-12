package Javastral.com.gestorMateriasWeb.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Javastral.com.gestorMateriasWeb.model.entity.ERole;
import Javastral.com.gestorMateriasWeb.model.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}