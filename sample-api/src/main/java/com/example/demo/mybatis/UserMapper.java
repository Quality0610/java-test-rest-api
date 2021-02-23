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

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM user;")
	public List<User> getUsers() throws DataAccessException;
	
	@Select("SELECT * FROM user WHERE name like #{name}")
	public List<User> getUsersByName(String name) throws DataAccessException;
	
	@Select("SELECT * FROM user WHERE id = #{id}")
	public User getUserById(String id) throws DataAccessException;
	
	@Insert("INSERT INTO user("
			+ "name ,"
			+ "age ,"
			+ "company) "
			+ "VALUES("
			+ " #{name},"
			+ " #{age},"
			+ " #{company})")
	public void insertUser(UserPostForm postForm) throws DataAccessException;
	
	@Select("SELECT MAX(id) "
			+ "FROM user")
	public String getLastUserId() throws DataAccessException;
	
	@Update("UPDATE user "
			+ "SET name = #{name} ,"
			+ "age = #{age} ,"
			+ "company = #{company} "
			+ "WHERE id = #{id}")
	public void updateUser(String id, UserPutForm putForm) throws DataAccessException;
	
	@Delete("DELETE FROM user "
			+ "WHERE id = #{id}")
	public void deleteUserById(String id) throws DataAccessException;
	

	
}
