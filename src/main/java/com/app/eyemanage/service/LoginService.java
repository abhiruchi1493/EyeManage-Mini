package com.app.eyemanage.service;

import org.springframework.data.jpa.repository.Query;

public interface LoginService {
	
	@Query(value="select count(*) from UserPOJO u where u.userName=?1 and u.password=?2")
	Integer validateLogin(String userName, String password);
}
