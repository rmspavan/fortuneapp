package org.fortune.application.bean;

import org.springframework.stereotype.Component;

@Component
public class HealthCheck 
{
	private String health;

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	
	
}
