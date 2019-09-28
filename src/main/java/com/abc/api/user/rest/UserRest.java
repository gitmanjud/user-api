package com.abc.api.user.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.api.exception.AuthorizedException;
import com.abc.api.exception.NotFoundException;
import com.abc.api.user.model.AuthenticationModel;
import com.abc.api.user.model.AuthenticationResponse;
import com.abc.api.user.model.ForgotPwdReponse;
import com.abc.api.user.model.PasswordModel;
import com.abc.api.user.model.UserModel;
import com.abc.api.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserRest {

	@Autowired
	private UserService userService;

	@GetMapping("/alive")
	public String alive() {
		return "success";
	}

	@PostMapping
	public @ResponseBody UserModel create(@RequestBody @Valid UserModel model) throws Exception {
		log.info("create user >>>>>>>>" + model);
		UserModel newModel = null;
		newModel = userService.createUser(model);
		log.info("create user <<<<<<" + newModel);
		return newModel;
	}

	@GetMapping(value = "/{id}")
	public @ResponseBody UserModel get(@PathVariable String id) throws NotFoundException, Exception {
		log.info("get user >>>>>>>>" + id);
		UserModel newModel = null;
		newModel = userService.getUser(id);
		log.info("get user <<<<<<" + newModel);
		return newModel;
	}

	@PutMapping("/{id}")
	public @ResponseBody UserModel update(@RequestBody @Valid UserModel model, @PathVariable String id)
			throws NotFoundException, Exception {
		log.info("update user >>>>>>>>" + id + ">>>>" + model);
		UserModel newModel = null;
		model.setId(id);
		newModel = userService.updateUser(model);
		log.info("update user <<<<<<" + id + "<<<<" + newModel);
		return newModel;
	}

	@DeleteMapping(value = "/{id}")
	public @ResponseBody UserModel delete(@PathVariable String id) throws NotFoundException, Exception {
		log.info("delete user >>>>>>>>" + id);
		UserModel newModel = null;
		newModel = userService.deleteUser(id);
		log.info("delete user <<<<<<" + newModel);
		return newModel;
	}

	@PutMapping("/pwd/{id}")
	public @ResponseBody PasswordModel updatePWD(@RequestBody PasswordModel model, @PathVariable String id)
			throws Exception {
		log.info("updatePWD user >>>>>>>>" + id + ">>>>PasswordModel >>>>>" + model);
		PasswordModel newModel = null;
		model.setUserId(id);
		newModel = userService.updatePwd(model);
		log.info("updatePWD user <<<<<<<<<<<< " + newModel);
		return newModel;
	}

	@PostMapping("/forgotpwd/{id}")
	public @ResponseBody List<ForgotPwdReponse> forgotPWD(@PathVariable String id) throws Exception {
		log.info("forgotPWD user >>>>>>>>" + id);
		List<ForgotPwdReponse> newModel = null;
		newModel = userService.forgotPwd(id);
		log.info("forgotPWD user <<<<<<<<<" + newModel);
		return newModel;
	}

	@PostMapping(value = "/authentication")
	public @ResponseBody AuthenticationResponse authentication(@RequestBody AuthenticationModel model)
			throws AuthorizedException, Exception {
		log.info("authentication user >>>>>>>>" + model);
		AuthenticationResponse authenticationResponse = null;
		authenticationResponse = userService.authenticateUser(model);
		log.info("authentication user >>>>>>>>" + authenticationResponse);
		return authenticationResponse;
	}

}
