package com.app.eyemanage.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class ForgotPassword {
	private String userName;
	private String secQuest;
	private String answer;
	private String newPassword;
	private String confirmPassword;
	
	
	public ForgotPassword() {
		// TODO Auto-generated constructor stub
	}
	
	public ForgotPassword(String userName, String secQuest, String answer) {
		super();
		this.userName = userName;
		this.secQuest = secQuest;
		this.answer = answer;
	}
	
	
	@NotNull
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@NotNull
	@Column(length=100)
	public String getSecQuest() {
		return secQuest;
	}
	public void setSecQuest(String secQuest) {
		this.secQuest = secQuest;
	}
	
	@NotNull
	@Column(length=30)
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@NotNull
	@Column(length=16)
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@NotNull
	@Column(length=16)
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "ForgotPassword [userName=" + userName + ", secQuest=" + secQuest + ", answer=" + answer + ", newPassword="
				+ newPassword + ", confirmPassword=" + confirmPassword + "]";
	}
	
}
