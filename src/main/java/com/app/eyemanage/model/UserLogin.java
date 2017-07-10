package com.app.eyemanage.model;

import org.apache.log4j.Logger;
import com.app.eyemanage.controller.UserController;
import lombok.Data;

@Data
public class UserLogin {
	private String userName;
	private String password;
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	public UserLogin() {
		logger.info("Default Constructor User Login");
	}
	
	public UserLogin(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
}
