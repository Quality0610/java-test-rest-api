package com.example.demo.validation;

import org.springframework.stereotype.Component;

@Component
public class ControllerValidation {
	
	public boolean isNumber(String id) {
		
		if(id.matches("^-?\\d+$")) {
			return true;			
		}

		return false;
	} 

}
