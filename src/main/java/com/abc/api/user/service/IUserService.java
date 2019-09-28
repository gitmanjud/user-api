package com.abc.api.user.service;

import java.util.List;

import com.abc.api.user.model.AuthenticationModel;
import com.abc.api.user.model.AuthenticationResponse;
import com.abc.api.user.model.ForgotPwdReponse;
import com.abc.api.user.model.PasswordModel;
import com.abc.api.user.model.UserModel;

public interface IUserService {

	public UserModel createUser(UserModel model)throws Exception;
	
	public UserModel updateUser(UserModel model)throws Exception;
	
	public AuthenticationResponse authenticateUser(AuthenticationModel model) throws Exception;
	
	public UserModel getUser(String searchParam) throws Exception;
	
	public UserModel deleteUser(String searchParam) throws Exception;
	
	public PasswordModel updatePwd(PasswordModel model)throws Exception;
	
	public List<ForgotPwdReponse> forgotPwd(String id)throws Exception;
	
}
