package com.app.eyemanage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.app.eyemanage.pojo.PatientDetailsPOJO;
import com.app.eyemanage.pojo.VisitPOJO;
import com.app.eyemanage.service.PatientService;
import com.app.eyemanage.service.VisitService;
import com.app.eyemanage.utility.Utils;

@Controller
@RequestMapping("/dashboard")
public class VisitController {

	private static final Logger logger = Logger.getLogger(VisitController.class);
	
	@Autowired
	VisitService visitService;
	
	@Autowired
	VisitPOJO visit;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	PatientDetailsPOJO patient;
	
	@RequestMapping(value="/visit" , method=RequestMethod.GET)
	public String patient(Model model, HttpSession session) {
		logger.info("Visit Home Get");
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return "redirect:/";
		}
		else {
			return "visit";
		}
	}
	
	@RequestMapping(value="/newVisit",method=RequestMethod.GET)
	public String showVisit( @RequestParam("id") String id , Model model, HttpSession session ) {
		logger.info("New Visit Get");
		logger.info("Patient Id : " + id);
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return "redirect:/";
		}
		else {
			model.addAttribute("pId", id);
			model.addAttribute("newVisit",visit);
			return "visitAdd";
		}
	}
	
	@RequestMapping(value="/newVisit/{id}",method=RequestMethod.POST)
	public String addVisit( @ModelAttribute("newVisit") VisitPOJO visit, @PathVariable("id")String id, ModelMap modelMap ) {
		logger.info("New Visit Post");
		logger.info("Patient Id : " + id);
		try {
			logger.info("Try");
			patient	=	patientService.findOne(Integer.parseInt(id));
			logger.info("Patient Obj : " + patient.toString());
			if( null == patient ) {
				modelMap.addAttribute("visitCreateResult", false );
				//return "redirect:/dashboard/patientSearch?visitCreateResult";
				return "redirect:/dashboard/visit?visitCreateResult";
			}
			else {
				logger.info("Patient Obj : " + patient.toString());
				visit.setPatient(patient);
				visit.setVisitDate(new Date());
				logger.info(visit.toString());
				String visitId	=	visitService.addVisit(visit);
				if( null != visitId ) {
					logger.info("Visit Successfully Created");
					modelMap.addAttribute("visitCreateResult", true );
					//return "redirect:/dashboard/newVisit?id=" + visitId;
					return "redirect:/dashboard/visit?visitCreateResult=" + visitId;
				}
				modelMap.addAttribute("visitCreateResult", false );
				return "redirect:/dashboard/patientSearch?visitCreateResult";
			}
		} catch (Exception e) {
			logger.info("Catch : " + e);
			modelMap.addAttribute("visitCreateResult", false );
			return "redirect:/dashboard/patientSearch?visitCreateResult";
		}
	}
	
	@RequestMapping(value="/visitHistory/{id}")
	public ModelAndView visitHistory( @PathVariable("id")String patientId, ModelMap modelMap ) {
		logger.info("Visit History Post");
		logger.info("Patient Id : " + patientId);
		patient.setPatientId(Integer.parseInt(patientId));
		try {
			logger.info("Try");
			List<VisitPOJO> visits	=	visitService.findVisitsByPatientId(patient);
			logger.info("Visits List : " + visits.toString());
			modelMap.addAttribute("visitObj",visit);
			return new ModelAndView("visitSearch", "visitList", visits);
			
		} catch (Exception e) {
			logger.info("Catch : " + e);
			//modelMap.addAttribute("visitCreateResult", false );
			modelMap.addAttribute("visitObj",visit);
			return new ModelAndView("visitSearch", HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value="/visitSearch" , method=RequestMethod.GET)
	public String visitSearch(Model model, HttpSession session) {
		logger.info("Visit Search Get");
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return "redirect:/";
		}
		else {
			model.addAttribute("visitObj",visit);
			return "visitSearch";
		}
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value="/visitSearch" , method=RequestMethod.POST)
	public String visitSearchPost( @ModelAttribute("visitObj")VisitPOJO visit, Model model, HttpSession session) {
		logger.info("Visit Search Post");
		List<VisitPOJO> visits	=	new ArrayList<>();
		try {
			logger.info("Try");
			visits	=	visitService.findVisitByName(visit.getSearchText().toLowerCase());
			logger.info("List : " + visits.toString());
			logger.info("Try end");
		} catch (Exception e) {
			logger.info("catch : " + e);
		}
		finally {
			logger.info("Finally Block");
			if(visits.contains(null)) {
				logger.info("Nothing found");
			}
			else {
				//if( patientDetails.size() <= 1 &&  )
				model.addAttribute("visitList", visits);
			}
			return "visitSearch";
		}
	}
	
}
