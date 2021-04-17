package com.example.demo.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.model.form.UserPostForm;
import com.example.demo.model.form.UserPutForm;
import com.example.demo.model.response.GetsUsersResponse;
import com.example.demo.model.response.UserResponse;
import com.example.demo.service.ErrorService;
import com.example.demo.service.MyBatisService;
import com.example.demo.validation.ControllerValidation;

import lombok.NonNull;

@RestController
@RequestMapping("/v1/users")
public class UserController {
	
	@Autowired
	MyBatisService myBatisService;
	
	@Autowired
	ControllerValidation controllerValidation; 
	
	@Autowired
	ErrorService errorService;
	
	@GetMapping
	public ResponseEntity<GetsUsersResponse> getUsers() {
				
		try {
			return new ResponseEntity<>(
					this.returnGetsUsersResponseSetting(myBatisService.getUsers()), new HttpHeaders(), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(
			@PathVariable("id") String id) {
		
		if (!controllerValidation.isNumber(id)){
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

		try {
			User user = myBatisService.getUserById(id);
			
			if(Objects.isNull(user)) {
				return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);	
			}
			return new ResponseEntity<>(this.returnUserResponseSetting(user), new HttpHeaders(), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@PostMapping
	public ResponseEntity<UserResponse> postUser(
			@RequestBody @Validated UserPostForm postForm) {
		
		try {
			return new ResponseEntity<>(
				this.returnUserResponseSetting(myBatisService.insertUser(postForm)), new HttpHeaders(), HttpStatus.OK);
					
		} catch(DataAccessException e) {
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> putUserById(
			@PathVariable("id") String id, 
			@RequestBody UserPutForm putForm) {

		if (!controllerValidation.isNumber(id)){
			return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
		try {
			return new ResponseEntity<>(
					this.returnUserResponseSetting(myBatisService.updateUser(id, putForm)), new HttpHeaders(), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(
					new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserResponse> deleteUserById(
			@PathVariable("id") String id) {
		
		if (!controllerValidation.isNumber(id)){
			return new ResponseEntity<>(
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
		try {
			User user = myBatisService.deleteUserById(id);
			if(Objects.isNull(user)) {
				return new ResponseEntity<>(
					new HttpHeaders(), HttpStatus.NOT_FOUND);	
			}
			return new ResponseEntity<>(
				this.returnUserResponseSetting(user), new HttpHeaders(), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@NonNull
	private GetsUsersResponse returnGetsUsersResponseSetting(List<User> users) {
		GetsUsersResponse getsUsersResponse = new GetsUsersResponse();
		getsUsersResponse.setUserList(users);
		return getsUsersResponse;
	}
	
	@NonNull
	private UserResponse returnUserResponseSetting(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUser(user);
		return userResponse;
	}
}
