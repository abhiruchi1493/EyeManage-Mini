package com.app.eyemanage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.eyemanage.pojo.PatientDetailsPOJO;
import com.app.eyemanage.service.PatientService;
import com.app.eyemanage.utility.Utils;

@Controller
@RequestMapping("/dashboard")
public class PatientController {
	
	@Autowired
	PatientDetailsPOJO patientDetails;
	
	@Autowired
	PatientService patientService;
	
	private static final Logger logger = Logger.getLogger(PatientController.class);
	
	@RequestMapping(value="/patient" , method=RequestMethod.GET)
	public String patient(Model model, HttpSession session) {
		logger.info("Patient Home Get");
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return "redirect:/";
		}
		else {
			return "patient";
		}
	}
	
	@RequestMapping(value="/patientCreate" , method=RequestMethod.GET)
	public String showPatient(Model model, HttpSession session) {
		logger.info("Patient Add Get");
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return "redirect:/";
		}
		else {
			model.addAttribute("newPatient",patientDetails);
			return "patientAdd";
		}
	}
	
	@RequestMapping(value="/patientCreate",method=RequestMethod.POST)
	public String createPatient(@ModelAttribute("newPatient")PatientDetailsPOJO patient,ModelMap modelMap) {
		logger.info("Patient Add Post");
		try {
			logger.info("Patient Add Try Block");
			logger.info("BirthDate : " + patient.getBirthDate());
			patient.setFirstVisitDate(new Date());
			String newId	=	patientService.add(patient);
			if(null != newId){
				logger.info("Patient Successfully Added");
				modelMap.addAttribute("patientCreateResult", newId );
				return "redirect:/dashboard/patient?patientCreateResult=" + newId;
			}
			logger.info("Failed to Add.");
			modelMap.addAttribute("patientCreateResult", false);
			return "redirect:/dashboard/patientCreate?patientCreateResult";
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Patient Add Catch Block");
			modelMap.addAttribute("patientCreateResult", false);
			return "redirect:/dashboard/patientCreate?patientCreateResult";
		}
		
	}
	
	
	@RequestMapping(value="/patientSearch" , method=RequestMethod.GET)
	public String viewPatient(Model model, HttpSession session, @RequestParam("mode")String getMode) {
		logger.info("Patient View Get");
		logger.info("Get Mode : " + getMode);
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return "redirect:/";
		}
		else {
			model.addAttribute("viewPatient",patientDetails);
			model.addAttribute("getMode", getMode);
			return "patientSearch";
		}
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value= {"/patientSearch/{getMode}", "/patientSearch"},method=RequestMethod.POST)
	public String viewPatient(@ModelAttribute("viewPatient")PatientDetailsPOJO patient,
			@PathVariable("getMode") String postMode, ModelMap modelMap) {
		logger.info("Patient View Post");
		logger.info("Post Mode : " + postMode);
		List<PatientDetailsPOJO> patientDetails	=	new ArrayList<>();
		try {
			logger.info("Try Block");
			PatientDetailsPOJO patientResult;
			if (patient.getSearchFilter().equalsIgnoreCase("id")) {
				logger.info("Searching By Id");
				patientResult	=	patientService.findOne(Integer.parseInt(patient.getSearchText()));
				logger.info("Searching By Id Successful");
				patientDetails.add(patientResult);
			}
			else if (patient.getSearchFilter().equalsIgnoreCase("name")) {
				logger.info("Searching By Name");
				patientDetails	=	patientService.findPatientByName(patient.getSearchText().toLowerCase());
				logger.info(patientDetails.toString());
				logger.info("Searching By Name Successful");
			}
			else if(patient.getSearchFilter().equalsIgnoreCase("age")) {	// for age
				logger.info("Searching By Age");
				patientDetails	=	patientService.findPatientByAge(Integer.parseInt(patient.getSearchText()));
				logger.info("Searching By Age Successful");
			}
			else if(patient.getSearchFilter().equalsIgnoreCase("")) {	// for all
				logger.info("Searching All");
				patientDetails		=	patientService.findAllPatients();
				logger.info("Searching Successful");
			}
			
		} catch (NullPointerException e) {
			logger.info("Catch Block");
			logger.info("Searching All");
			patientDetails		=	patientService.findAllPatients();
			logger.info("Searching Successful");
			
		} finally {
			logger.info("Finally Block");
			if(patientDetails.contains(null)) {
				logger.info("Nothing found");
			}
			else {
				//if( patientDetails.size() <= 1 &&  )
				modelMap.addAttribute("patientDetails",patientDetails);
				modelMap.addAttribute("postMode", postMode);
			}
			return "patientSearch";
		}
	}
}