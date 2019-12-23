package com.sureshtech.springbootrestdemo.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sureshtech.springbootrestdemo.entity.User;
import com.sureshtech.springbootrestdemo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	ResourceBundleMessageSource messageResource;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("")
	public User saveUser(@RequestBody @Valid User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Integer id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/greet/{userName}")
	
	public String helloUser(@RequestHeader(name="Accept-Language") String language, @PathVariable("userName") String  userName) {
		
		return messageResource.getMessage("label.Welcome",null,new Locale(language))+ " "+userName;
	}
	

}
