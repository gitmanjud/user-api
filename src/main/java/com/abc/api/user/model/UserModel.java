package com.abc.api.user.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserModel {

	private String id;
	private String resource="USER";
	@NotBlank
	private String firstName;
	private String middleName;
	private String lastName;
	@NumberFormat
	private Integer mobileNo;
	private String gender;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date bdate;
	private int age;
	private String password;
	private Date created; 
	private Date updated;
	private String email;
	private String userId;
	private boolean active;
	private String ans;
	private String question;
	private LocationModel location;
	
	
}
