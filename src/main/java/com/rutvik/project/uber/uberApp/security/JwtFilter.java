package com.rutvik.project.uber.uberApp.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.rutvik.project.uber.uberApp.entities.User;
import com.rutvik.project.uber.uberApp.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


//Here we require User which implements UserDetailService because we need this user to add in the SecurityContextHolder
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final UserService us;

	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver handlerExceptionResolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			final String requestTokenHeader=request.getHeader("Authorization");
			if(requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer")){
				filterChain.doFilter(request, response);
				return;
			}
			
			String token=requestTokenHeader.split("Bearer ")[1];
			Long userId=jwtService.getUserIdFromToken(token);
			
			if(userId!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				User user=us.getUserById(userId);
				UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
				
			}
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			handlerExceptionResolver.resolveException(request, response, null, e);
		}

	}
}
