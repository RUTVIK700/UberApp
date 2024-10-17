package com.rutvik.project.uber.uberApp.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${jwt.sceretKey}")
	private String jwtSecretKey;
	
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateAccessToken(User user) {
		return Jwts.builder()
				.subject(user.getId().toString())
				.claim("email", user.getEmail())
				.claim("role", user.getRole().toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+1000*60*10))
				.signWith(getSecretKey())
				.compact();
	}
	public String generateRefreshToken(User user) {
		return Jwts.builder()
				.subject(user.getId().toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+1000L*60*100*24*30*6))
				.signWith(getSecretKey())
				.compact();
	}
	public Long getUserIdFromToken(String token) {
		Claims clamin=Jwts.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return Long.valueOf(clamin.getSubject());
	}
}
