package gr.codelearn.spring.showcase.app.transfer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
	private String jwtToken;
}
