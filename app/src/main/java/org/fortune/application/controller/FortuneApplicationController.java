package org.fortune.application.controller;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.fortune.application.bean.HealthCheck;
import org.fortune.application.bean.HealthStatus;
import org.fortune.application.bean.Message;
import org.fortune.application.service.FortuneApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FortuneApplicationController 
{
	@Autowired
	private FortuneApplicationService fortuneApplicationService;
	@Autowired
	private Message message;
	@Autowired
	private HealthCheck healthCheck;
	
	@GetMapping(value = {"/v1/fortune"})
	public Message getFortuneRandomMessage() 
	{
		message.setMessage(fortuneApplicationService.getFortuneRandomMessage());
		return message;
	}
	
	@GetMapping(value = {"/hostaddress"})
	public String getHostAddress() throws UnknownHostException  
	{
		return Inet4Address.getLocalHost().getHostAddress();
	}
	
	@GetMapping(value = {"/healthcheck"})
	public HealthCheck getHealthCheck() throws UnknownHostException 
	{
		String resourceUrl=Inet4Address.getLocalHost().getHostAddress();
		RestTemplate restTemplate=new RestTemplate();
		HealthStatus healthStatus = restTemplate.getForObject("http://"+resourceUrl+":8081"+"/health", HealthStatus.class);
		if(healthStatus.getStatus().equalsIgnoreCase("UP")) {
			healthCheck.setHealth("true");
		}else {
			healthCheck.setHealth("false");
		}
		return healthCheck;
	}
	
}
