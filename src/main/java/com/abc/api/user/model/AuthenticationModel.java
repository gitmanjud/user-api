package com.abc.api.user.model;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthenticationModel {

	private String email;
	@NotBlank
	private String password;
	private String userId;
	private String mobileNo;
	
}
