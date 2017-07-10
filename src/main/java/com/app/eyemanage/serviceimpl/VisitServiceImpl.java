package com.app.eyemanage.serviceimpl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.app.eyemanage.pojo.PatientDetailsPOJO;
import com.app.eyemanage.pojo.VisitPOJO;
import com.app.eyemanage.service.VisitService;

@ComponentScan
@Service
@Transactional
public class VisitServiceImpl {
private static final Logger logger = Logger.getLogger(VisitServiceImpl.class);
	
	@Autowired
	VisitService visitService;
	
	public VisitServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public VisitServiceImpl(VisitService visitService) {
		super();
		this.visitService = visitService;
	}

	@Transactional
	String addVisit(VisitPOJO visitDetails) {
		logger.info("VisitServiceImpl , Visit ::: " + visitDetails.toString() );
		
		VisitPOJO visitAdd	=	visitService.save(visitDetails);
		logger.info("Returned Object::: " + visitAdd.toString());
		if( !visitAdd.getVisitId().equals(null)){
			return visitAdd.getVisitId().toString();
		}
		else {
			logger.info("Visit could not be created !!!");
			return null;
		}
	}

}
