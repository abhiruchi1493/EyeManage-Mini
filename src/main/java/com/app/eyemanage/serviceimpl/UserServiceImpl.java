package com.app.eyemanage.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;
import com.app.eyemanage.model.ForgotPassword;
import com.app.eyemanage.pojo.UserPOJO;
import com.app.eyemanage.service.UserService;
import com.app.eyemanage.utility.Utils;

@ComponentScan
@Service
@Transactional
public class UserServiceImpl {
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserPOJO userPojo;
	
	public UserServiceImpl(UserService userService) {
		this.userService = userService;
	}

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional
	public Integer add(UserPOJO userDetails) {
		logger.info("User Service Impl , User ::: " + userDetails.toString());
		if(userService.validateUserName(userDetails.getUserName()) <= 0) {
			if(Utils.validatePass(userDetails.getPassword(), userDetails.getCnfPassword())) {
				UserPOJO demoUser	=	this.userService.save(userDetails);
				logger.info("Successfully added, Returned Object::: " + demoUser.toString());
					if( !demoUser.getUserName().equalsIgnoreCase(null)){
						return 1;	// Successfully Added
					}
					else {
						logger.info("User could not be created !!!");
						return 2;	// Failed to Add
					}
			}
			else {
				logger.info("Passwords do not match !!!");
				return 3;	// Passwords Do No Match
			}
		}
		else {
			logger.info("UserName already exists !!!");
			return 4;	// User Name already exists
		}
	}
	
	@Transactional
	public boolean forgotPassCheck(ForgotPassword forgotUser) {
		logger.info("forgotPassCheck method");
		try {
			if(Utils.validatePass(forgotUser.getNewPassword(),forgotUser.getConfirmPassword()) && 
					 (userService.updatePassword(forgotUser.getNewPassword(), forgotUser.getUserName()) > 0) ) {
				return true;
			}
			return false;
		}
		catch (Exception e) {
			return false;
		}
			
	}
}
