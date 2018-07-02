package net.sunil.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class GenericResponse {

	private String status;
	
	private String errorMessage;
	
	private Object payLoad;
	
	private String token;
	
	public GenericResponse(String status, String string, Object payLoad) {
	
		this.status = status;
		if(this.status.equals(AppConstants.GENERIC_RESPONSE_SUCCESS) ) {
			this.token = string;	
		}else {
			this.errorMessage = string;
		}
		this.payLoad = payLoad;
	}
	
	public GenericResponse(String status, String string) {
		
		this.status = status;
		if(this.status.equals(AppConstants.GENERIC_RESPONSE_SUCCESS) ) {
			this.token = string;	
		}else {
			this.errorMessage = string;
		}
	}

	public GenericResponse(String status, Object payLoad) {
		
		this.status = status;
		this.payLoad = payLoad;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(Object payLoad) {
		this.payLoad = payLoad;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
