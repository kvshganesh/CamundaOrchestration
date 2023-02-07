package com.camundaDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class CamundaDemo {
	
	public static void main(String... args) {
		SpringApplication.run(CamundaDemo.class, args);
	}
	
	@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI().info(new Info()
	      .title("Camunda integrated with Spring boot and swagger")
	      .version("Spring doc version : 1.6.12, Spring boot version : 2.6.6 And Camunda Version : 7.17"  )
	      .description("This is a sample application to demonstrate camunda workflow using spring boot and springdocs."));
	}
}
