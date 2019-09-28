package com.abc.api.user.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.api.exception.AuthorizedException;
import com.abc.api.exception.NotFoundException;
import com.abc.api.user.dao.UserDao;
import com.abc.api.user.entity.UserEntity;
import com.abc.api.user.model.AuthenticationModel;
import com.abc.api.user.model.AuthenticationResponse;
import com.abc.api.user.model.ForgotPwdReponse;
import com.abc.api.user.model.PasswordModel;
import com.abc.api.user.model.UserModel;
import com.abc.api.user.model.UserModelAssembler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService {

	@Autowired
	private UserModelAssembler userModelAssembler;

	@Autowired
	private UserDao userDao;

	@Override
	public UserModel createUser(UserModel model) throws Exception {
		log.debug("create user >>>>>>>>" + model);
		UserModel newModel = null;
		try {
			UserEntity entity = null;
			model.setUserId(genarateUserId(model.getFirstName()));
			entity = userModelAssembler.assemble(model);
			log.debug("UserEntity >>>>>" + entity);
			UserEntity newEntity = userDao.save(entity);
			log.debug("UserEntity >>>>>" + entity);
			newModel = userModelAssembler.assemble(newEntity);
		} catch (Exception e) {
			log.error("Exception while creating user " + e.getMessage());
			throw e;
		}
		log.debug("create user <<<<<<" + newModel);
		return newModel;
	}

	@Override
	public UserModel getUser(String searchParam) throws NotFoundException, Exception {
		log.debug("get user >>>>>>>>" + searchParam);
		UserModel newModel = null;
		try {
			UserEntity entity = userDao.findByUserIdOrId(searchParam, searchParam);
			log.debug("UserEntity >>>>>>>>" + entity);
			if (null != entity) {
				newModel = userModelAssembler.assemble(entity);
			} else {
				throw new NotFoundException("invalid Id");
			}
		} catch (NotFoundException e) {
			log.error("NotFoundException while getUser " + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Exception while getUser " + e.getMessage());
			throw e;
		}
		log.debug("get user <<<<<<" + newModel);
		return newModel;
	}

	@Override
	public UserModel updateUser(UserModel model) throws NotFoundException, Exception {
		log.debug("update user >>>>>>>>" + model);
		UserModel newModel = null;
		try {
			UserEntity entity = userDao.findByUserIdOrId(model.getId(), model.getId());
			log.debug("UserEntity >>>>>>>>" + entity);
			if (null != entity) {
				entity = userModelAssembler.assemble(model, entity);
				UserEntity dbEntity = userDao.save(entity);
				log.debug("UserEntity >>>>>>>>" + dbEntity);
				newModel = userModelAssembler.assemble(dbEntity);
			} else {
				throw new NotFoundException("invalid Id");
			}
		} catch (NotFoundException e) {
			log.error("NotFoundException while updateUser " + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Exception while updateUser " + e.getMessage());
			throw e;
		}
		log.debug("update user <<<<<<<<" + newModel);
		return newModel;
	}

	@Override
	public UserModel deleteUser(String searchParam) throws NotFoundException, Exception {
		log.debug("delete user >>>>>>>>" + searchParam);
		UserModel newModel = null;
		try {
			UserEntity entity = userDao.findByUserIdOrId(searchParam, searchParam);
			log.debug("UserEntity >>>>>>>>" + entity);
			if (null != entity) {
				entity.setActive(false);
				userDao.save(entity);
				newModel = userModelAssembler.assemble(entity);
			} else {
				throw new NotFoundException("invalid Id");
			}
		} catch (NotFoundException e) {
			log.error("NotFoundException while deleteUser " + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Exception while deleteUser " + e.getMessage());
			throw e;
		}
		log.debug("delete user <<<<<<<<" + newModel);
		return newModel;
	}

	@Override
	public PasswordModel updatePwd(PasswordModel model) throws NotFoundException, Exception {
		log.debug("updatePwd user PasswordModel >>>>>>>>>>>>>" + model);
		try {
			UserEntity entity = userDao.findByUserIdOrId(model.getUserId(), model.getUserId());
			log.debug("updatePwd user UserEntity <<<<<<<<" + entity);
			if (null == entity) {
				throw new NotFoundException("invalid Id");
			}
			if (StringUtils.equals(model.getCurrentPwd(), entity.getPassword())) {
				entity.setPassword(model.getNewPwd());
				UserEntity dbEntity = userDao.save(entity);
				model.setCurrentPwd(dbEntity.getPassword());
			} else {
				throw new Exception("password didnot match");
			}
		} catch (NotFoundException e) {
			log.error("NotFoundException while updatePwd " + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Exception while updatePwd " + e.getMessage());
			throw e;
		}
		log.debug("updatePwd user PasswordModel <<<<<<<<" + model);
		return model;
	}

	@Override
	public List<ForgotPwdReponse> forgotPwd(String id) throws Exception {
		log.debug("forgotPwd user ForgotPwdReponse >>>>>" + id);
		List<ForgotPwdReponse> resList = new ArrayList<>();
		try {
			UserEntity entity = userDao.findByUserIdOrId(id, id);
			if (null != entity) {
				resList.add(userModelAssembler.assemblePwd(entity));
			} else {
				List<UserEntity> entityList = userDao.findByMobileNo(id);
				if (CollectionUtils.isNotEmpty(entityList)) {
					for (UserEntity userEntity : entityList) {
						resList.add(userModelAssembler.assemblePwd(userEntity));
					}
				} else {
					throw new NotFoundException("invalid Id");
				}

			}
		} catch (NotFoundException e) {
			log.error("NotFoundException while updatePwd " + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Exception while updatePwd " + e.getMessage());
			throw e;
		}
		log.debug("forgotPwd user ForgotPwdReponse <<<<<<<<" + resList);
		return resList;
	}

	@Override
	public AuthenticationResponse authenticateUser(AuthenticationModel model) throws AuthorizedException, Exception {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		log.debug("authenticateUser <<<<<<<<" + model);
		try {
			UserEntity entity = null;
			if (StringUtils.isNotBlank(model.getEmail())) {
				entity = userDao.findByEmail(model.getEmail());
			} else if (StringUtils.isNotBlank(model.getUserId())) {
				entity = userDao.findByUserId(model.getUserId());
			}
			if (null != entity && StringUtils.equals(model.getPassword(), entity.getPassword())) {
				authenticationResponse.setUserId(entity.getUserId());
				authenticationResponse.setSuccess(true);
			} else {
				throw new AuthorizedException("invalid creds");
			}
		} catch (AuthorizedException e) {
			log.error("AuthorizedException while authenticateUser " + e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Exception while authenticateUser " + e.getMessage());
			throw e;
		}
		log.debug("authenticateUser AuthenticationResponse<<<<<<<<" + authenticationResponse);
		return authenticationResponse;
	}

	private String genarateUserId(String fname) throws Exception {
		Long count = 0L;
		String userId = "";
		try {
			if (StringUtils.isEmpty(fname)) {
				fname = "test";
			}
			count = userDao.count();
			userId = fname.concat(count.toString());
			UserEntity entity = userDao.findByUserId(userId);
			if (null != entity) {
				count++;
				userId = fname.concat(count.toString());
			}
		} catch (Exception e) {
			throw e;
		}
		return userId;
	}

}
