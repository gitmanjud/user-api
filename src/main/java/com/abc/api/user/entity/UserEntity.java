package com.abc.api.user.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.abc.api.user.model.LocationModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection="user")
@Getter
@Setter
@ToString
public class UserEntity {

	@Id
	private String id;
	private String resource="USER";
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer mobileNo;
	private String gender;
	private Date bdate; 
	private String email; 
	private String password;
	private Date created; 
	private Date updated;
	private boolean active;
	private String question;
	private String ans;
	private String userId;
	private LocationEntity location;
	
	
}
