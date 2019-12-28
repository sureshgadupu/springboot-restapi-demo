package com.sureshtech.springbootrestdemo.mapper;

import org.mapstruct.Mapper;

import com.sureshtech.springbootrestdemo.dto.UserDtoV1;
import com.sureshtech.springbootrestdemo.dto.UserDtoV2;
import com.sureshtech.springbootrestdemo.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	
  UserDtoV1 userToUserDtoV1(User user);
  
  UserDtoV2 userToUserDtoV2(User user);
  
}
