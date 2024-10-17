package com.rutvik.project.uber.uberApp.advices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.exceptions.RuntimeConflictException;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(RuntimeConflictException.class)
	public ResponseEntity<ApiResponse<?>> handlerunTimeConflictException(RuntimeConflictException expection) {
		ApiError api = new ApiError(HttpStatus.CONFLICT, expection.getMessage(), null);
		return buildErrorResponseEntity(api);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
		ApiError api = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage(), null);
		return buildErrorResponseEntity(api);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> GlobalhandleExpection(Exception expection) {
		ApiError api = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, expection.getMessage(), null);
		return buildErrorResponseEntity(api);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> inputhandleExpection(MethodArgumentNotValidException expection) {
		List<String> list = expection.getBindingResult().getAllErrors().stream().map(i -> i.getDefaultMessage())
				.collect(Collectors.toList());
		ApiError api = new ApiError(HttpStatus.BAD_REQUEST, "Input Validation Failed", list);
		return buildErrorResponseEntity(api);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException exception) {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage(), null);
		return buildErrorResponseEntity(apiError);
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException exception) {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage(), null);
		return buildErrorResponseEntity(apiError);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException exception) {
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, exception.getLocalizedMessage(), null);
		return buildErrorResponseEntity(apiError);
	}

	private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError api) {
		return new ResponseEntity<>(new ApiResponse<>(api), api.getStatus());
	}

}
