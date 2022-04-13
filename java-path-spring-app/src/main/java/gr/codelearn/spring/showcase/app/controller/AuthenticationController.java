package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.service.AuthenticationService;
import gr.codelearn.spring.showcase.app.transfer.AuthenticationRequest;
import gr.codelearn.spring.showcase.app.transfer.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService authenticationService;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest authenticationRequest) {

		String jwtToken = authenticationService.authenticate(authenticationRequest.getUsername(),
															 authenticationRequest.getPassword());
		AuthenticationResponse authenticationResponse = AuthenticationResponse.builder().jwtToken(jwtToken).build();
		return ResponseEntity.ok(authenticationResponse);
	}
}
