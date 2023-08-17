package com.socialMediaDevelpoer.socialMediaDevelpoer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@OpenAPIDefinition
@SpringBootApplication
public class SocialMediaDevelpoerApplication
{
	public static void main(String[] args) {
		SpringApplication.run(SocialMediaDevelpoerApplication.class, args);
	}
}
