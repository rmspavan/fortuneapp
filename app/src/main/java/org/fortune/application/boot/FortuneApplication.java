package org.fortune.application.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.fortune.application.*"})
public class FortuneApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(FortuneApplication.class, args);
	}

}
