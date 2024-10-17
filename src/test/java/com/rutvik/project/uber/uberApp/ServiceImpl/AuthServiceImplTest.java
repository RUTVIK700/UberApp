package com.rutvik.project.uber.uberApp.ServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rutvik.project.uber.uberApp.dtos.SignUpDto;
import com.rutvik.project.uber.uberApp.dtos.UserDto;
import com.rutvik.project.uber.uberApp.entities.User;
import com.rutvik.project.uber.uberApp.enums.Roles;
import com.rutvik.project.uber.uberApp.repositories.UserRepository;
import com.rutvik.project.uber.uberApp.security.JwtService;
import com.rutvik.project.uber.uberApp.services.impl.AuthServiceImpl;
import com.rutvik.project.uber.uberApp.services.impl.DriverServiceImpl;
import com.rutvik.project.uber.uberApp.services.impl.RiderServiceImpl;
import com.rutvik.project.uber.uberApp.services.impl.WalletServiceImpl;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtService jwtService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RiderServiceImpl rideServiceImpl;

	@Mock
	private WalletServiceImpl walletServiceImpl;

	@Mock
	private DriverServiceImpl driveServiceImpl;

	@Spy
	private ModelMapper modelMapper;

	@Spy
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private AuthServiceImpl authServiceImpl;

	private User user;
	private SignUpDto signUpDto;

	@BeforeEach
	public void setup() {
		user = new User();
		user.setId(1L);
		user.setEmail("testEmail@gmail.com");
		user.setPassword("password");
		user.setRole(Set.of(Roles.RIDER));

		signUpDto = new SignUpDto();
		signUpDto.setEmail("testEmail@gmail.com");
		signUpDto.setPassword("password");
	}

	@Test
	void testLogin_whenSuccess() {
		//Given
		Authentication authentication=mock(Authentication.class);
		when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
		when(authentication.getPrincipal()).thenReturn(user);
		when(jwtService.generateAccessToken(any(User.class))).thenReturn("accessToken");
		when(jwtService.generateRefreshToken(any(User.class))).thenReturn("refreshToken");
		
		//When
		String[] tokens=authServiceImpl.login(user.getEmail(), user.getPassword());
		
		//Then
		assertThat(tokens).hasSize(2);
		assertThat(tokens[0]).isEqualTo("accessToken");
		assertThat(tokens[1]).isEqualTo("refreshToken");
		
	}
	
	@Test
	void testSignup_whenSuccess() {
		//Given
		when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		//When
		UserDto userDto=authServiceImpl.signup(signUpDto);
		
		//Then
		assertThat(userDto).isNotNull();
		assertThat(userDto.getEmail()).isEqualTo(signUpDto.getEmail());
		verify(rideServiceImpl).createNewRider(any(User.class));
		verify(walletServiceImpl).createNewWallet(any(User.class));
	}

}
