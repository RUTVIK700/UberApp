package com.rutvik.project.uber.uberApp.services.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.entities.User;
import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.repositories.UserRepository;
import com.rutvik.project.uber.uberApp.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	private final UserRepository userRepository;
	@Override
	public User getUserById(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with Id "+userId));
		return user;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).orElseThrow(()-> new BadCredentialsException("User Not Found with "+username));
	}

}
