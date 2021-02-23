package com.example.demo.controller;

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
import com.example.demo.model.response.GetsResponse;
import com.example.demo.model.response.UserResponse;
import com.example.demo.service.ErrorService;
import com.example.demo.service.MyBatisService;
import com.example.demo.validation.ControllerValidation;

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
	public ResponseEntity<Object> getUsers(
			@RequestParam(name = "name", required = false) String name
			) {
				
		if(!Objects.isNull(name)) {
			return new ResponseEntity<>(
					new GetsResponse(myBatisService.getUsersByName(name)), new HttpHeaders(), HttpStatus.OK);			
		}
		
		try {
			return new ResponseEntity<>(
					new GetsResponse(myBatisService.getUsers()), new HttpHeaders(), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(errorService.getInternalServerError(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserById(
			@PathVariable("id") String id) {
		
		if (!controllerValidation.isNumber(id)){
			return new ResponseEntity<>(
					errorService.getBadRequestError(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

		try {
			User user = myBatisService.getUserById(id);
			
			if(Objects.isNull(user)) {
				return new ResponseEntity<>(
						errorService.getNotFoundError(), new HttpHeaders(), HttpStatus.NOT_FOUND);	
			}
			return new ResponseEntity<>(
					new UserResponse(user), new HttpHeaders(), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(
					errorService.getInternalServerError(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@PostMapping
	public ResponseEntity<Object> postUser(
			@RequestBody @Validated UserPostForm postForm) {
		
		try {
			return new ResponseEntity<>(
					new UserResponse(myBatisService.insertUser(postForm)), new HttpHeaders(), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(
					errorService.getInternalServerError(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putUserById(
			@PathVariable("id") String id, 
			@RequestBody UserPutForm putForm) {

		if (!controllerValidation.isNumber(id)){
			return new ResponseEntity<>(
					errorService.getBadRequestError(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
		try {
			return new ResponseEntity<>(
					new UserResponse(myBatisService.updateUser(id, putForm)), new HttpHeaders(), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(
					errorService.getInternalServerError(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUserById(
			@PathVariable("id") String id) {
		
		if (!controllerValidation.isNumber(id)){
			return new ResponseEntity<>(
					errorService.getBadRequestError(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
		try {
			User user = myBatisService.deleteUserById(id);
			if(Objects.isNull(user)) {
				return new ResponseEntity<>(
						errorService.getNotFoundError(), new HttpHeaders(), HttpStatus.NOT_FOUND);	
			}
			return new ResponseEntity<>(
					new UserResponse(user), new HttpHeaders(), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(
					errorService.getInternalServerError(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
