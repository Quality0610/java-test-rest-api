package com.example.demo.model.response;

import java.util.List;

import com.example.demo.model.User;

import lombok.Data;
import lombok.NonNull;

// GETでid指定しないエンドポイントでの返却レスポンス

@Data
public class GetsUsersResponse {
	
	public GetsUsersResponse() {

	}

	@NonNull
	List<User> userList;
	
}
