package Javastral.com.gestorMateriasWeb.security.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Javastral.com.gestorMateriasWeb.model.entity.RefreshToken;
import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;
import Javastral.com.gestorMateriasWeb.model.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RefreshTokenService {

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	@Value("${jwt.refresh.expiration:604800000}")
	private long refreshTokenTtlMs;

	private final RefreshTokenRepository refreshTokenRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public RefreshTokenDescriptor create(UserEntity user, boolean invalidateExisting) {
		Instant now = Instant.now();
		if (invalidateExisting) {
			List<RefreshToken> activeTokens = this.refreshTokenRepository.findByUserAndRevokedAtIsNull(user);
			for (RefreshToken token : activeTokens) {
				token.setRevokedAt(now);
			}
		}

		TokenParts parts = this.generateTokenParts();
		RefreshToken refreshToken = RefreshToken.builder()
				.tokenId(parts.tokenId())
				.tokenHash(this.passwordEncoder.encode(parts.secret()))
				.user(user)
				.expiresAt(now.plus(this.refreshTokenTtlMs, ChronoUnit.MILLIS))
				.build();

		refreshToken = this.refreshTokenRepository.save(refreshToken);

		return new RefreshTokenDescriptor(this.composeTokenValue(parts), refreshToken.getExpiresAt());
	}

	@Transactional(noRollbackFor = RefreshTokenException.class)
	public RefreshTokenRotation rotate(String presentedTokenValue) {
		TokenValue parsedValue = this.parseTokenValue(presentedTokenValue);
		RefreshToken stored = this.refreshTokenRepository.findByTokenId(parsedValue.tokenId())
				.orElseThrow(() -> new RefreshTokenException("Refresh token not found."));

		Instant now = Instant.now();
		if (stored.isRevoked() || stored.isExpired(now)) {
			stored.setRevokedAt(now);
			throw new RefreshTokenException("Refresh token is expired or revoked.");
		}

		boolean matches = this.passwordEncoder.matches(parsedValue.secret(), stored.getTokenHash());
		stored.setRevokedAt(now);

		if (!matches) {
			throw new RefreshTokenException("Refresh token signature mismatch.");
		}

		RefreshTokenDescriptor newDescriptor = this.create(stored.getUser(), false);
		return new RefreshTokenRotation(stored.getUser(), newDescriptor);
	}

	@Transactional
	public void revokeAllForUser(UserEntity user) {
		Instant now = Instant.now();
		List<RefreshToken> activeTokens = this.refreshTokenRepository.findByUserAndRevokedAtIsNull(user);
		for (RefreshToken token : activeTokens) {
			token.setRevokedAt(now);
		}
	}

	private TokenParts generateTokenParts() {
		UUID tokenId = UUID.randomUUID();
		byte[] randomBytes = new byte[48];
		SECURE_RANDOM.nextBytes(randomBytes);
		String secret = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
		return new TokenParts(tokenId, secret);
	}

	private String composeTokenValue(TokenParts parts) {
		return parts.tokenId() + "." + parts.secret();
	}

	private TokenValue parseTokenValue(String rawToken) {
		if (rawToken == null || rawToken.isBlank() || !rawToken.contains(".")) {
			throw new RefreshTokenException("Refresh token is malformed.");
		}

		String[] pieces = rawToken.split("\\.", 2);
		if (pieces.length != 2) {
			throw new RefreshTokenException("Refresh token structure invalid.");
		}

		UUID tokenId;
		try {
			tokenId = UUID.fromString(pieces[0]);
		} catch (IllegalArgumentException ex) {
			throw new RefreshTokenException("Refresh token identifier invalid.");
		}

		if (pieces[1].isBlank()) {
			throw new RefreshTokenException("Refresh token secret missing.");
		}

		return new TokenValue(tokenId, pieces[1]);
	}

	public record RefreshTokenDescriptor(String tokenValue, Instant expiresAt) { }

	public record RefreshTokenRotation(UserEntity user, RefreshTokenDescriptor descriptor) { }

	private record TokenParts(UUID tokenId, String secret) { }

	private record TokenValue(UUID tokenId, String secret) { }

	public static class RefreshTokenException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public RefreshTokenException(String message) {
			super(message);
		}
	}
}
