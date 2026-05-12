package Javastral.com.gestorMateriasWeb.model.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Javastral.com.gestorMateriasWeb.model.entity.RefreshToken;
import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByTokenId(UUID tokenId);

	List<RefreshToken> findByUserAndRevokedAtIsNull(UserEntity user);
}
