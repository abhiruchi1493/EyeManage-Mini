package com.app.eyemanage.utility;

import javax.servlet.http.HttpSession;

public class Utils {
	
	public Utils() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean validatePass(String pass,String cnfPass) {
		
		return pass.equals(cnfPass);
	}
	
	public static boolean validateSession( HttpSession session, String attribute) {
		try {
			if ( null == session.getAttribute(attribute))
				return false;
			else
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			return true;
		}
	}
	
}
