package com.rutvik.project.uber.uberApp.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rutvik.project.uber.uberApp.security.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	private final String[] paths = { "/auth/**", "/home.html" };
	private final JwtFilter jwtFilter;
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity
//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers(paths).permitAll()
//						.anyRequest().authenticated())
//				.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.csrf(csrf -> csrf.disable())
//				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//						
//		
//		
//		
//		return httpSecurity.build();
//
//	}
	
	@Bean
	public SecurityFilterChain security(HttpSecurity hs) throws Exception {
		hs.authorizeHttpRequests(auth -> auth.requestMatchers(paths).permitAll()
				.anyRequest().authenticated())
		.csrf(csrf->csrf.disable())
		.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return hs.build();
	}
}


