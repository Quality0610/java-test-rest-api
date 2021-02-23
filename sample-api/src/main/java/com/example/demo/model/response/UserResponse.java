package com.example.demo.model.response;

import com.example.demo.model.User;

import lombok.Data;
import lombok.NonNull;

// GET, POST, PUT, DELETEの返却レスポンス

@Data
public class UserResponse {
	
	@NonNull
	User user;
}
