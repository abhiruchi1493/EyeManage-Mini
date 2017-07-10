package com.app.eyemanage.model;

import com.app.eyemanage.pojo.PatientDetailsPOJO;
import lombok.Data;

@Data
public class Response {
	
	private String status;
	private Object data;
	public Response(String string, PatientDetailsPOJO p) {
		// TODO Auto-generated constructor stub
		status=string;
		data=p;
	}

}
