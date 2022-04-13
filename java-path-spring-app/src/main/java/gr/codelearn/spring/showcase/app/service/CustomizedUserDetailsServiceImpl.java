package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomizedUserDetailsServiceImpl extends BaseComponent implements UserDetailsService {
	private final List<UserDetails> userList = new ArrayList<>();
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@PostConstruct
	public void initializeUserList() {
		//@formatter:off
		userList.add(User.withUsername("admin")
						 .password(passwordEncoder.encode("admin123"))
						 .roles("ADMIN", "USER")
						 .build());
		userList.add(User.withUsername("user")
						 .password(passwordEncoder.encode("user123"))
						 .roles("USER").build());
		//@formatter:on

		logger.trace("Initialized user sample list.");
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserDetails userDetails = findUserByUsername(username);
		if (userDetails != null) {
			return userDetails;
		} else {
			throw new UsernameNotFoundException(String.format("User %s was not found", username));
		}
	}

	private UserDetails findUserByUsername(final String username) {
		for (UserDetails userDetails : userList) {
			if (userDetails.getUsername().equals(username)) {
				return userDetails;
			}
		}
		return null;
	}
}
