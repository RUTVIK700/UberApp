package com.rutvik.project.uber.uberApp.controllers;


import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rutvik.project.uber.uberApp.dtos.AmountDto;
import com.rutvik.project.uber.uberApp.dtos.DriverDto;
import com.rutvik.project.uber.uberApp.dtos.LogOutDto;
import com.rutvik.project.uber.uberApp.dtos.LoginRequestDto;
import com.rutvik.project.uber.uberApp.dtos.LoginResponseDto;
import com.rutvik.project.uber.uberApp.dtos.SignUpDto;
import com.rutvik.project.uber.uberApp.dtos.UserDto;
import com.rutvik.project.uber.uberApp.dtos.VehicleDto;
import com.rutvik.project.uber.uberApp.dtos.WalletDto;
import com.rutvik.project.uber.uberApp.entities.User;
import com.rutvik.project.uber.uberApp.entities.Wallet;
import com.rutvik.project.uber.uberApp.enums.TranscationMethod;
import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.services.AuthService;
import com.rutvik.project.uber.uberApp.services.UserService;
import com.rutvik.project.uber.uberApp.services.WalletService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final WalletService walletService;
	private final UserService userService;
	private final ModelMapper modelMapper;

	@PostMapping("/signUp")
	public ResponseEntity<UserDto> requestRide(@RequestBody SignUpDto signUpDto) {
		return new ResponseEntity<>(authService.signup(signUpDto), HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/onBoardNewDriver/{userId}")
	public ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId, @RequestBody VehicleDto vehicleDto) {
		return new ResponseEntity<DriverDto>(authService.onboardNewDriver(userId, vehicleDto.getVehicle(),vehicleDto.getDriverLocation()),
				HttpStatus.CREATED);
	}

	@PostMapping("/addMoneyToWallet/{userId}")
	public ResponseEntity<WalletDto> addMoneyToWallet(@PathVariable Long userId, @RequestBody AmountDto amount) {
		User userById = userService.getUserById(userId);
		Wallet wallet = walletService.addMoney(userById, amount.getAmount(), null, null, TranscationMethod.BANKING);
		WalletDto walletDto = modelMapper.map(wallet, WalletDto.class);
		return ResponseEntity.ok(walletDto);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> requestRide(@RequestBody LoginRequestDto loginRequestDto,
			HttpServletResponse httpServletResponse) {
		String[] tokens = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
		Cookie cookie = new Cookie("tokens", tokens[1]);
		cookie.setSecure(false);// set this to false in local host and true on production. We want it true in
		cookie.setHttpOnly(true);						// production because this ONLY allows HTTPS request on production
		httpServletResponse.addCookie(cookie);
		return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
	}

	@PostMapping("/logout")
	public ResponseEntity<Boolean> logout(@RequestBody LogOutDto logOutDto,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		boolean logout = authService.logout(logOutDto);
		Cookie Usercookie =Arrays.stream(httpServletRequest.getCookies()).filter(cookie->"refreshToken".equals(cookie.getName()))
				.findFirst().orElseThrow(()->new ResourceNotFoundException("No Cookie received in this request"));
		Usercookie.setValue(null);
		httpServletResponse.addCookie(Usercookie);
		return ResponseEntity.ok(logout);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<LoginResponseDto> GettingRefreshToken(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		String refreshToken = Arrays.stream(request.getCookies())
		.filter(cookie -> "refreshToken".equals(cookie.getName()))
		.findFirst()
		.map(cookie->cookie.getValue())
		.orElseThrow(()->new AuthenticationException("Refresh Token Not Found Inside Cookies"));
		
		 String newAccessToken = authService.refreshToken(refreshToken);
		 
		
		return ResponseEntity.ok(modelMapper.map(newAccessToken,LoginResponseDto.class));
	}
}
