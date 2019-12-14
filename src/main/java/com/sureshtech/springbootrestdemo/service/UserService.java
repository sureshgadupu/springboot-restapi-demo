package com.sureshtech.springbootrestdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sureshtech.springbootrestdemo.entity.User;
import com.sureshtech.springbootrestdemo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(Integer id) {
		
		return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No User found with Id :"+id));
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}

}
