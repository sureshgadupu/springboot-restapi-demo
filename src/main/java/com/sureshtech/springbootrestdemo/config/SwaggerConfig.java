package com.sureshtech.springbootrestdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.base.Predicates;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	public Docket getApi() {
		System.out.println("Docker");
		return  new  Docket(DocumentationType.SWAGGER_2)
						
						.apiInfo(getApiInfo())
						.select()
						.apis(RequestHandlerSelectors.basePackage("com.sureshtech.springbootrestdemo.controller"))
						.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
						.paths(PathSelectors.regex("*/posts/*"))
						.paths(Predicates.not(PathSelectors.regex("/error.+")))
						//.paths(PathSelectors.regex("(?!/error).+"))
						.build();		
								
	}

	private ApiInfo getApiInfo() {
		
		
		return new ApiInfoBuilder()
				.title("Swagger UI Example")
				.description("Swagger UI Example")
				.version("1.0")
				.license("v2.0")
				.contact(new Contact("Suresh", "www.sureshtech.com"," suresh@abc.com"))
				.build();
	}
}
	