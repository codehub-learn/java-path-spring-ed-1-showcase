package gr.codelearn.spring.showcase.app.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JwtService {
	String getUsernameFromToken(String token) throws JwtException;

	Date getExpirationDateFromToken(String token) throws JwtException;

	<T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws JwtException;

	String generateToken(UserDetails userDetails) throws JwtException;

	boolean validateToken(String token, UserDetails userDetails);
}
