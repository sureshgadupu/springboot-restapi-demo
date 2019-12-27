package com.sureshtech.springbootrestdemo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.sureshtech.springbootrestdemo.dto.PostMsDto;
import com.sureshtech.springbootrestdemo.entity.Post;

@Mapper	(componentModel   = "spring")
public interface PostMapper {
	
	PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
	
	@Mappings(value = { @Mapping(source = "content",target= "postContent") , @Mapping(source = "createdOn",target= "createdDate") })
	
	PostMsDto PostToPostMsDto(Post post);

	
	List<PostMsDto> PostTsoPostMsDtos(List<Post> post);
 }
