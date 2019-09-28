package com.abc.api.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthenticationResponse {

	private String userId;
	private boolean success;
	
}
