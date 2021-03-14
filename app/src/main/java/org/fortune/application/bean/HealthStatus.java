package org.fortune.application.bean;

import org.springframework.stereotype.Component;

@Component
public class HealthStatus 
{
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
