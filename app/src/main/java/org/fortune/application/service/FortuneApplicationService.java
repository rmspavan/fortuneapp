package org.fortune.application.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class FortuneApplicationService 
{
	public String getFortuneRandomMessage() 
	{
		 int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 50;
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (random.nextFloat() * (rightLimit - leftLimit + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String generatedString = buffer.toString();
		    return generatedString;
	}

}
