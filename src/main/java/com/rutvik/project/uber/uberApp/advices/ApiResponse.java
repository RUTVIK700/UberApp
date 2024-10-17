package com.rutvik.project.uber.uberApp.advices;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiResponse<T> {

	@JsonFormat(pattern = "hh-mm-ss dd-MM-yyyy")
	private LocalDateTime time;
	private T data;
	private ApiError error;

	public ApiError getError() {
		return error;
	}

	public void setError(ApiError error) {
		this.error = error;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ApiResponse(T data) {
		this();
		this.data = data;
	}

	public ApiResponse(ApiError error) {
		this();
		this.error = error;
	}

	public ApiResponse() {
		this.time = LocalDateTime.now();
	}

}
