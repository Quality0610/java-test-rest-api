package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class User {

	@NonNull
	@JsonProperty("id")
	Integer id;
	
	@NonNull
	@JsonProperty("name")
	String name;
	
	@NonNull
	@JsonProperty("age")
	Integer age;
	
	@NonNull
	@JsonProperty("company")
	String company;
	
}
