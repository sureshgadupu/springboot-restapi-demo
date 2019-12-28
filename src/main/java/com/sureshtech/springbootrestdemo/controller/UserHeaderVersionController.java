package com.sureshtech.springbootrestdemo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sureshtech.springbootrestdemo.dto.UserDtoV1;
import com.sureshtech.springbootrestdemo.dto.UserDtoV2;
import com.sureshtech.springbootrestdemo.mapper.UserMapper;
import com.sureshtech.springbootrestdemo.service.UserService;

@RestController
@RequestMapping("versioning/header/users")
public class UserHeaderVersionController {
	
	@Autowired
	ResourceBundleMessageSource messageResource;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
//	@PostMapping("")
//	public User saveUser(@RequestBody @Valid User user) {
//		return userService.saveUser(user);
//	}
	
	@GetMapping(value= "/{id}" , headers = "API-VERSION=1")
	public UserDtoV1 getUserByIdV1(@PathVariable("id") Integer id) {
		return userMapper.userToUserDtoV1(userService.getUserById(id));
	}
	
	@GetMapping(value= "/{id}" , headers = "API-VERSION=2")
	public UserDtoV2 getUserByIdV2(@PathVariable("id") Integer id) {
		return userMapper.userToUserDtoV2(userService.getUserById(id));
	}
	
	@GetMapping("/greet/{userName}")
	
	public String helloUser(@RequestHeader(name="Accept-Language") String language, @PathVariable("userName") String  userName) {
		
		return messageResource.getMessage("label.Welcome",null,new Locale(language))+ " "+userName;
	}
	

}
