package Javastral.com.gestorMateriasWeb.security.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import Javastral.com.gestorMateriasWeb.security.payload.LoginRequest;
import Javastral.com.gestorMateriasWeb.security.payload.SignupRequest;
import Javastral.com.gestorMateriasWeb.security.service.AuthService;
import Javastral.com.gestorMateriasWeb.security.service.RefreshTokenService.RefreshTokenDescriptor;
import Javastral.com.gestorMateriasWeb.security.service.RefreshTokenService.RefreshTokenException;
import Javastral.com.gestorMateriasWeb.security.payload.MessageResponse;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

@CrossOrigin(originPatterns = "${app.cors.allowed-origins:http://localhost:5173}", allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
		var loginResult = this.authService.login(loginRequest);

		ResponseCookie refreshCookie = this.buildRefreshCookie(loginResult.refreshToken(), request);

		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
				.body(loginResult.jwtResponse());
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return this.authService.signUp(signUpRequest);
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshAccessToken(HttpServletRequest request) {
		String rawRefreshToken = this.extractRefreshToken(request);
		if (rawRefreshToken == null) {
			ResponseCookie cleared = this.expireRefreshCookie(request);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.header(HttpHeaders.SET_COOKIE, cleared.toString())
					.body(new MessageResponse("Refresh token is missing."));
		}

		try {
			var result = this.authService.refreshAccessToken(rawRefreshToken);
			ResponseCookie refreshCookie = this.buildRefreshCookie(result.refreshToken(), request);
			return ResponseEntity.ok()
					.header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
					.body(result.jwtResponse());
		} catch (RefreshTokenException ex) {
			ResponseCookie cleared = this.expireRefreshCookie(request);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.header(HttpHeaders.SET_COOKIE, cleared.toString())
					.body(new MessageResponse("Refresh token is invalid."));
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof org.springframework.security.core.userdetails.UserDetails userDetails) {
				this.authService.logout(userDetails.getUsername());
			} else if (principal instanceof String principalName && !"anonymousUser".equals(principalName)) {
				this.authService.logout(principalName);
			}
		}

		ResponseCookie cleared = this.expireRefreshCookie(request);
		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, cleared.toString())
				.body(new MessageResponse("Sesión cerrada"));
	}

	private ResponseCookie buildRefreshCookie(RefreshTokenDescriptor descriptor, HttpServletRequest request) {
		long ttlMillis = descriptor.expiresAt().toEpochMilli() - Instant.now().toEpochMilli();
		long maxAgeSeconds = ttlMillis > 0 ? (ttlMillis + 999) / 1000L : 0L;
		return ResponseCookie.from("refresh_token", descriptor.tokenValue())
				.httpOnly(true)
				.secure(request.isSecure())
				.sameSite("Lax")
				.path("/")
				.maxAge(Duration.ofSeconds(maxAgeSeconds))
				.build();
	}

	private String extractRefreshToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0) {
			return null;
		}
		return Arrays.stream(cookies)
				.filter(cookie -> "refresh_token".equals(cookie.getName()))
				.findFirst()
				.map(Cookie::getValue)
				.orElse(null);
	}

	private ResponseCookie expireRefreshCookie(HttpServletRequest request) {
		return ResponseCookie.from("refresh_token", "")
				.httpOnly(true)
				.secure(request.isSecure())
				.sameSite("Lax")
				.path("/")
				.maxAge(Duration.ZERO)
				.build();
	}
}
