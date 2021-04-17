package com.example.demo.model.response;

import com.example.demo.model.User;

import lombok.Data;
import lombok.NonNull;

// GET(id指定), POST, PUT, DELETEの返却レスポンス

@Data
public class UserResponse {
	
	public UserResponse() {
	}

	@NonNull
	User user;
	
}
