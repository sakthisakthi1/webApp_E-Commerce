package com.johndeere.adzone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 
public class OrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			//@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/v1/api/**").allowCredentials(true).allowedHeaders("*")
						.allowedOriginPatterns()
						.allowedMethods("HEAD", "GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
  			}
		};
	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
 }
