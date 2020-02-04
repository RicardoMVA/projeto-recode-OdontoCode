package com.qualiti.odontoSystem;

import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

public class AppConfig {
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
	     return new MethodValidationPostProcessor();
	}
}
