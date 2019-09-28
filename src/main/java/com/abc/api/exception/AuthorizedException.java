package com.abc.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
public class AuthorizedException extends Exception {

	private String msg;

	AuthorizedException() {

	}

	public AuthorizedException(String msg) {
		super(msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "AuthorizedException [msg=" + msg + "]";
	}

}
