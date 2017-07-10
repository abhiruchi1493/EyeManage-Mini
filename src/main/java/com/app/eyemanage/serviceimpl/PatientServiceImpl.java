package com.app.eyemanage.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.eyemanage.pojo.PatientDetailsPOJO;
import com.app.eyemanage.service.PatientService;

@ComponentScan
@Service
@Transactional
public class PatientServiceImpl {

	private static final Logger logger = Logger.getLogger(PatientServiceImpl.class);
	
	@Autowired
	PatientService patientService;

	public PatientServiceImpl(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
	
	public PatientServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional
	String add(PatientDetailsPOJO patientDetails) {
		logger.info("PatientServiceImpl , Patient ::: " + patientDetails.toString());
		
		PatientDetailsPOJO patientAdd	=	patientService.save(patientDetails);
		logger.info("Returned Object::: " + patientAdd.toString());
		if( !patientAdd.getPatientId().equals(null)){
			return patientAdd.getPatientId().toString();
		}
		else {
			logger.info("Patient could not be added !!!");
			return null;
		}
	}
}
