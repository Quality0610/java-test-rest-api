package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class ErrorService {

	public String getInternalServerError() {
		return "{\"error\": \"DB Access failed \"}";
	}
	
	public String getBadRequestError() {
		return "{\"error\": \"Request is wrong \"}";
	}
	
	public String getNotFoundError() {
		return "{\"error\": \"User is Not Found \"}";
	}
}
