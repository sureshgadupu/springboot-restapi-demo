package com.sureshtech.springbootrestdemo;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class SpringbootRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestDemoApplication.class, args);
	}
	
	@Bean	
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
//	@Bean
//	public ResourceBundleMessageSource messageReSource() {
//		ResourceBundleMessageSource resourceBundle = new ResourceBundleMessageSource();
//		resourceBundle.setBasename("messages");
//		
//		return resourceBundle;
//		
//	}

}
