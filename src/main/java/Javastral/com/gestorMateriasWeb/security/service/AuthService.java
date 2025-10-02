package Javastral.com.gestorMateriasWeb.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Javastral.com.gestorMateriasWeb.security.jwt.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Javastral.com.gestorMateriasWeb.model.entity.ERole;
import Javastral.com.gestorMateriasWeb.model.entity.Role;
import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;
import Javastral.com.gestorMateriasWeb.model.repository.RoleRepository;
import Javastral.com.gestorMateriasWeb.model.repository.UserRepository;
import Javastral.com.gestorMateriasWeb.security.jwt.JwtUtils;
import Javastral.com.gestorMateriasWeb.security.payload.JwtResponse;
import Javastral.com.gestorMateriasWeb.security.payload.LoginRequest;
import Javastral.com.gestorMateriasWeb.security.payload.MessageResponse;
import Javastral.com.gestorMateriasWeb.security.payload.SignupRequest;
import Javastral.com.gestorMateriasWeb.security.service.RefreshTokenService.RefreshTokenDescriptor;
import Javastral.com.gestorMateriasWeb.security.service.RefreshTokenService.RefreshTokenRotation;

@Service
public class AuthService {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	RefreshTokenService refreshTokenService;
	
	public void logout(String username) {
		this.userRepository.findByUsername(username).ifPresent(user -> {
			this.refreshTokenService.revokeAllForUser(user);
		});
	}

	public LoginResult login(LoginRequest loginRequest) throws AuthenticationException {
		Authentication authentication = this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginRequest.getUsername(),
							loginRequest.getPassword())
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = this.jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		UserEntity userEntity = this.userRepository.findById(userDetails.getId())
				.orElseThrow(() -> new IllegalStateException("Authenticated user not found."));

		RefreshTokenDescriptor refreshDescriptor = this.refreshTokenService.create(userEntity, true);

		long expiration = this.jwtUtils.getExpirationTime();

		JwtResponse jwtResponse = new JwtResponse(
				jwt,
				expiration,
				null,
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles
		);

		return new LoginResult(jwtResponse, refreshDescriptor);
	}

	public LoginResult refreshAccessToken(String refreshTokenValue) {
		RefreshTokenRotation rotation = this.refreshTokenService.rotate(refreshTokenValue);
		UserEntity user = rotation.user();
		UserDetailsImpl userDetails = UserDetailsImpl.build(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetails,
				null,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = this.jwtUtils.generateJwtToken(authentication);
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		long expiration = this.jwtUtils.getExpirationTime();
		JwtResponse jwtResponse = new JwtResponse(
				jwt,
				expiration,
				null,
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles
		);
		return new LoginResult(jwtResponse, rotation.descriptor());
	}
	
	public ResponseEntity<?> signUp(SignupRequest signupRequest) {
		
		if(this.userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Username already exists."));
		}
		
		if(this.userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email already registered."));
		}
		
		UserEntity userEntity = new UserEntity(signupRequest.getUsername(), signupRequest.getEmail(),
				this.encoder.encode(signupRequest.getPassword()));
		
		Set<String> strRoles = signupRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.MOD)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		

		userEntity.setRoles(roles);
		this.userRepository.save(userEntity);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	public record LoginResult(JwtResponse jwtResponse, RefreshTokenDescriptor refreshToken) { }
}
