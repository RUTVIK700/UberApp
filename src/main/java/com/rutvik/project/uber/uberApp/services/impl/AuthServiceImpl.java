package com.rutvik.project.uber.uberApp.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rutvik.project.uber.uberApp.dtos.DriverDto;
import com.rutvik.project.uber.uberApp.dtos.LogOutDto;
import com.rutvik.project.uber.uberApp.dtos.LoginResponseDto;
import com.rutvik.project.uber.uberApp.dtos.SignUpDto;
import com.rutvik.project.uber.uberApp.dtos.UserDto;
import com.rutvik.project.uber.uberApp.entities.Driver;
import com.rutvik.project.uber.uberApp.entities.User;
import com.rutvik.project.uber.uberApp.enums.Roles;
import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.rutvik.project.uber.uberApp.repositories.UserRepository;
import com.rutvik.project.uber.uberApp.security.JwtService;
import com.rutvik.project.uber.uberApp.services.AuthService;
import com.rutvik.project.uber.uberApp.services.DriverService;
import com.rutvik.project.uber.uberApp.services.RiderService;
import com.rutvik.project.uber.uberApp.services.UserService;
import com.rutvik.project.uber.uberApp.services.WalletService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final RiderService riderService;
	private final WalletService walletService;
	private final DriverService driverService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserService userService;
	
	@Override
	public String[] login(String email, String password) {
		
		//This will authenticate User automatically from DB
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password));
		
		User user=(User)authentication.getPrincipal();
	
		String accessToken=jwtService.generateAccessToken(user);
		String refreshToken=jwtService.generateRefreshToken(user);
		
		String tokens[]= {accessToken,refreshToken};
		return tokens;
	}

	@Override
	@Transactional
	public UserDto signup(SignUpDto signUpDto) {
		User user=userRepository.findByEmail(signUpDto.getEmail()).orElse(null);
		if(user!=null) {
			 throw new RuntimeConflictException("User Exists With EmailId "+signUpDto.getEmail());
		}
		
		User mappedUser=modelMapper.map(signUpDto, User.class);
		mappedUser.setRole(Set.of(Roles.RIDER));
		mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
		User savedUser=userRepository.save(mappedUser);
		
		riderService.createNewRider(savedUser);
		walletService.createNewWallet(savedUser);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public DriverDto onboardNewDriver(Long userId,String vehicleId) {
		User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id "+userId));
		
		if(user.getRole().contains(Roles.DRIVER))
			throw new RuntimeException("User with Id" +userId+" is already a Driver");
		Driver createDriver=Driver.builder()
				.user(user)
				.rating(0.0)
				.vehicleId(vehicleId)
				.available(true)
				.build();
		user.getRole().add(Roles.DRIVER);
		userRepository.save(user);
		Driver savedDriver=driverService.createNewDriver(createDriver);
		return modelMapper.map(savedDriver, DriverDto.class);
	}

	@Override
	public boolean logout(LogOutDto logOutDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String refreshToken(String refreshToken) {
		Long userId = jwtService.getUserIdFromToken(refreshToken);
		User user = userService.getUserById(userId);
		String accessToken = jwtService.generateAccessToken(user);
		return accessToken;
	}

}
