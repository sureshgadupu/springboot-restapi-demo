package com.sureshtech.springbootrestdemo.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sureshtech.springbootrestdemo.dto.PostMsDto;
import com.sureshtech.springbootrestdemo.entity.Post;
import com.sureshtech.springbootrestdemo.mapper.PostMapper;
import com.sureshtech.springbootrestdemo.service.PostService;

@RestController
@RequestMapping("mapstruct/posts")
@CrossOrigin()
@Validated
public class PostMapStructController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private PostMapper postmapper;
	
	@GetMapping("")
	public List<PostMsDto> getAllPosts() {
		
		//PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
		return postmapper.PostTsoPostMsDtos(postService.getAllPosts());
	}
	
	
	
	@GetMapping(value="/{id}")
	public PostMsDto getPost(@PathVariable("id") @Min(1) Integer id) {
		//PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
		return postmapper.PostToPostMsDto( postService.getPost(id)) ;
		
	}
}
