package com.app.eyemanage.service;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.eyemanage.pojo.PatientDetailsPOJO;

@Repository
public interface PatientService extends CrudRepository<PatientDetailsPOJO, Integer>{
	
	String add(PatientDetailsPOJO patientDetails);
	
	@Query(value="from PatientDetailsPOJO p where (lower(p.firstName) like %:name%) or (lower(p.lastName) like %:name%)")
	List<PatientDetailsPOJO> findPatientByName(@Param("name") String name);
	
	@Query(value="from PatientDetailsPOJO p where p.age=?1")
	List<PatientDetailsPOJO> findPatientByAge(Integer age);
	
	@Query(value="from PatientDetailsPOJO p")
	List<PatientDetailsPOJO> findAllPatients();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update PatientDetailsPOJO p set p.firstName=?2, p.lastName=?3, "
			+ "p.gender=?4, p.email=?5, p.mobile=?6,  p.phoneNumber=?7 where p.patientId=?1")
	Integer editPatientDetails(Integer patientId,String firstName, String lastName, String gender, 
			String email, Long mobile, Long phoneNumber);
	
}
