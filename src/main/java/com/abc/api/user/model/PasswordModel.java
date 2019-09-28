package com.abc.api.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PasswordModel {

	private String userId;
	private String currentPwd;
	private String newPwd;
	
}
