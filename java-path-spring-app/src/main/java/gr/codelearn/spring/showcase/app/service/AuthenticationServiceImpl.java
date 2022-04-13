package gr.codelearn.spring.showcase.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final AuthenticationManager authenticationManager;

	@Override
	public String authenticate(final String username, final String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		var userDetails = userDetailsService.loadUserByUsername(username);
		return jwtService.generateToken(userDetails);
	}
}
