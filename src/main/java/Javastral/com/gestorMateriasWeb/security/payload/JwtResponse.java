package Javastral.com.gestorMateriasWeb.security.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long expiration;
	private String refreshToken;
	private userData user;

	public JwtResponse(String accessToken, Long expiration, String refreshToken, String username, String email, List<String> roles) {
		this.token = accessToken;
		this.expiration = expiration;
		this.refreshToken = refreshToken;
		this.user = new userData(username, email, roles);
	}
}

record userData(String username, String email, List<String> roles) {}
