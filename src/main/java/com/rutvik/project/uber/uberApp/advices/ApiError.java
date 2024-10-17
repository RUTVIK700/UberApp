package com.rutvik.project.uber.uberApp.advices;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
	private HttpStatus status;
	
	private String message;
	
	private List<String> sublist;
	
	

	public List<String> getSublist() {
		return sublist;
	}

	public void setSublist(List<String> sublist) {
		this.sublist = sublist;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiError(HttpStatus status, String message,List<String> sublist) {
		super();
		this.status = status;
		this.message = message;
		this.sublist=sublist;
	}
	
	
}
