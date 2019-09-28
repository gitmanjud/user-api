package com.abc.api.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ForgotPwdReponse {

	private String userId;
	private String question;
	private String ans;
	private String currentPwd;
	
}
