package com.example.demo.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.User;
import com.example.demo.model.form.UserPostForm;
import com.example.demo.model.form.UserPutForm;

public interface UserMapper {
	
	public List<User> getUsers() throws DataAccessException;

	public List<User> getUsersByName(String name) throws DataAccessException;
	
	public User getUserById(String id) throws DataAccessException;
	
	public void insertUser(UserPostForm postForm) throws DataAccessException;
	
	public String getLastUserId() throws DataAccessException;
	
	public void updateUser(String id, UserPutForm putForm) throws DataAccessException;
	
	public void deleteUserById(String id) throws DataAccessException;
	
}
