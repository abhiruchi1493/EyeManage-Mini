package com.app.eyemanage.controller;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.app.eyemanage.utility.Utils;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value="/home" , method=RequestMethod.GET)
	public String showdashboard(Model model, HttpSession session) {
		
		logger.info("Dashboard Get"); 
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return "redirect:/";
		}
		else {
			session.getAttribute("UserDetails");
			model.addAttribute("userName", session.getAttribute("UserDetails").toString());
			logger.info("Current User : " + (session.getAttribute("UserDetails")).toString());
			return "dashboard";
		}
	}
	/*
	@RequestMapping(value="/home" , method=RequestMethod.POST)
	public String dashboard(Model model, HttpSession session) {
		logger.info("Dashboard Post");
		try {
			session.invalidate();
			logger.info("Successfully Logged out");
			return "redirect:/";
		} catch (Exception e) {
			logger.info("Unable to Log out");
			e.printStackTrace();
			return "dashboard";
		}
	}
	*/
	@RequestMapping(value="/logout" , method=RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		logger.info("Logout Get");
		try {
			session.invalidate();
			logger.info("Successfully Logged out");
			return "redirect:/";
		} catch (Exception e) {
			logger.info("Unable to Log out");
			e.printStackTrace();
			return "redirect:/dashboard/home";
		}
	}
}
