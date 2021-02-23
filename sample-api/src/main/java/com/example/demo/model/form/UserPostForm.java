package com.example.demo.model.form;


import lombok.Data;
import lombok.NonNull;

@Data
public class UserPostForm {
	
	@NonNull
	String name;
	
	@NonNull
	Integer age;
	
	@NonNull
	String company;
}
