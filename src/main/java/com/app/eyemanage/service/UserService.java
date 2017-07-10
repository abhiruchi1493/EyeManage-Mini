package com.app.eyemanage.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.*;
import com.app.eyemanage.model.ForgotPassword;
import com.app.eyemanage.pojo.UserPOJO;

@Repository
public interface UserService extends CrudRepository<UserPOJO, Integer>,LoginService,SecQuestionService{
	Integer add(UserPOJO userDetails);
	
	@Query(value="select count(*) from UserPOJO u where u.userName=?1")
	Integer validateUserName(String userName);
	
	boolean forgotPassCheck(ForgotPassword forgotUser);
	
	@Query(value="select count(*) from UserPOJO u where u.userName=?1 and u.secQuest=?2 and u.answer=?3")
	Integer quesAnswerCheck(String userName, String question, String answer);
	
	@Modifying(clearAutomatically = true)
	@Query(value="update UserPOJO u set u.password=?1 where u.userName=?2")
	Integer updatePassword(String password, String userName);
}
