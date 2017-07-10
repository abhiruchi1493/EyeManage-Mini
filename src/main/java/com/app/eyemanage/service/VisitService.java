package com.app.eyemanage.service;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.app.eyemanage.pojo.PatientDetailsPOJO;
import com.app.eyemanage.pojo.VisitPOJO;

public interface VisitService extends CrudRepository<VisitPOJO, Integer>{

	String addVisit(VisitPOJO visitDetails);
	
	@Query(value="from VisitPOJO v where v.patient=?1")
	List<VisitPOJO> findVisitsByPatientId(PatientDetailsPOJO patient);
	
	@Query(value="from VisitPOJO v where (lower(v.patient.firstName) like %:name%) or (lower(v.patient.lastName) like %:name%)")
	List<VisitPOJO> findVisitByName(@Param("name") String name);
}
