package Javastral.com.gestorMateriasWeb.security.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String newPassword;
}