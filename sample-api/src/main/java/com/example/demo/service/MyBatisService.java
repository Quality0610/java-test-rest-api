package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.model.form.UserPostForm;
import com.example.demo.model.form.UserPutForm;
import com.example.demo.mybatis.UserMapper;

@Transactional
@Service
public class MyBatisService {

	@Autowired
	UserMapper userMapper;
	
	public List<User> getUsers() throws DataAccessException{
		
		return userMapper.getUsers();
	}
	
	public List<User> getUsersByName(String name) throws DataAccessException{
		return userMapper.getUsersByName(name);
	}

	
	public User getUserById(String id) throws DataAccessException{
		return userMapper.getUserById(id);
	}
	
	public User insertUser(UserPostForm postForm) throws DataAccessException{
		userMapper.insertUser(postForm);
		String id = userMapper.getLastUserId();
		return userMapper.getUserById(id);
	}
	
	public User updateUser(String id, UserPutForm putForm) throws DataAccessException{
		userMapper.updateUser(id, putForm);
		return userMapper.getUserById(id); 
	}
	
	public User deleteUserById(String id) throws DataAccessException{
		User user = userMapper.getUserById(id); 
		userMapper.deleteUserById(id);
		return user;
	}
}
