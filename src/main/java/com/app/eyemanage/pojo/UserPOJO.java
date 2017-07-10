package com.app.eyemanage.pojo;

import javax.persistence.*;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.*;

@Entity
@Table(name="Users")
@Component

public class UserPOJO {
	private String userName;
	private String name;
	private String password;
	private String email;
	private String cnfPassword;
	private String secQuest;
	private String answer;
	
	private static final Logger logger = Logger.getLogger(UserPOJO.class);
	public UserPOJO() {
		logger.info("Default Constructor UserPOJO");
	}
	
	public UserPOJO(String userName, String name, String password, String email) {
		super();
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	@Column(name = "userName", unique = true, nullable = false, length=20)
	@Id
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(length=30)
	@NotEmpty
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length=16)
	@NotEmpty
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(length=60)
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Transient
	public String getCnfPassword() {
		return cnfPassword;
	}

	public void setCnfPassword(String cnfPassword) {
		this.cnfPassword = cnfPassword;
	}
	
	@Column(length=100)
	public String getSecQuest() {
		return secQuest;
	}

	public void setSecQuest(String secQuest) {
		this.secQuest = secQuest;
	}
	
	@Column(length=30)
	@NotEmpty
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "UserPOJO [userName=" + userName + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", cnfPassword=" + cnfPassword + ", secQuest=" + secQuest + ", answer=" + answer + "]";
	}
}
