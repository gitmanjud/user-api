package com.abc.api.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.abc.api.user.entity.LocationEntity;
import com.abc.api.user.entity.UserEntity;

@Component
public class UserModelAssembler {

	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	public UserEntity assemble(UserModel model) {
		UserEntity entity = new UserEntity();
		entity.setFirstName(model.getFirstName());
		entity.setMiddleName(model.getMiddleName());
		entity.setLastName(model.getLastName());
		entity.setMobileNo(model.getMobileNo());
		entity.setGender(model.getGender());
		entity.setBdate(model.getBdate());
		entity.setPassword(model.getPassword());
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		entity.setUserId(model.getUserId());
		entity.setActive(true);
		if (null != model.getLocation()) {
			LocationEntity locationEntity = new LocationEntity();
			locationEntity.setCountry(model.getLocation().getCountry());
			locationEntity.setState(model.getLocation().getState());
			locationEntity.setDist(model.getLocation().getDist());
			locationEntity.setTq(model.getLocation().getTq());
			locationEntity.setRegion(model.getLocation().getRegion());
			entity.setLocation(locationEntity);
		}
		entity.setQuestion(model.getQuestion());
		entity.setAns(model.getAns());
		return entity;
	}
	
	
	public UserEntity assemble(UserModel model, UserEntity entity) {
		entity.setFirstName(model.getFirstName());
		entity.setMiddleName(model.getMiddleName());
		entity.setLastName(model.getLastName());
		entity.setMobileNo(model.getMobileNo());
		entity.setGender(model.getGender());
		entity.setBdate(model.getBdate());
		entity.setPassword(StringUtils.isNotBlank(model.getPassword()) ? model.getPassword() : entity.getPassword());
		entity.setUpdated(new Date());
		entity.setUserId(model.getUserId());
		entity.setActive(true);
		entity.setQuestion(model.getQuestion());
		entity.setAns(model.getAns());
		if (null != model.getLocation()) {
			LocationEntity locationEntity = new LocationEntity();
			locationEntity.setCountry(model.getLocation().getCountry());
			locationEntity.setState(model.getLocation().getState());
			locationEntity.setDist(model.getLocation().getDist());
			locationEntity.setTq(model.getLocation().getTq());
			locationEntity.setRegion(model.getLocation().getRegion());
			entity.setLocation(locationEntity);
		}
		return entity;
	}
	

	public UserModel assemble(UserEntity entity) {
		UserModel model = new UserModel();
		model.setId(entity.getId());
		model.setFirstName(entity.getFirstName());
		model.setMiddleName(entity.getMiddleName());
		model.setLastName(entity.getLastName());
		model.setMobileNo(entity.getMobileNo());
		model.setGender(entity.getGender());
		model.setBdate(entity.getBdate());
		model.setUserId(entity.getUserId());
		model.setActive(entity.isActive());
		//pwd should not return 
		// model.setPassword(entity.getPassword());
		model.setCreated(entity.getCreated());
		model.setUpdated(entity.getUpdated());
		if (null != entity.getLocation()) {
			LocationModel locationModel = new LocationModel();
			locationModel.setCountry(entity.getLocation().getCountry());
			locationModel.setState(entity.getLocation().getState());
			locationModel.setDist(entity.getLocation().getDist());
			locationModel.setTq(entity.getLocation().getTq());
			locationModel.setRegion(entity.getLocation().getRegion());
			model.setLocation(locationModel);
		}
		model.setQuestion(entity.getQuestion());
		model.setAns(entity.getAns());
		return model;
	}
	
	public ForgotPwdReponse assemblePwd(UserEntity entity) {
		ForgotPwdReponse response = new ForgotPwdReponse();
		response.setUserId(entity.getUserId());
		response.setQuestion(entity.getQuestion());
		response.setAns(entity.getAns());
		response.setCurrentPwd(entity.getPassword());
		return response;
	
	}
	
}
