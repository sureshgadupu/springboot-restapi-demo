package com.sureshtech.springbootrestdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sureshtech.springbootrestdemo.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer>{

}
