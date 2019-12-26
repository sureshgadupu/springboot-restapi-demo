package com.sureshtech.springbootrestdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sureshtech.springbootrestdemo.dto.PostMmDto;
import com.sureshtech.springbootrestdemo.entity.Post;
import com.sureshtech.springbootrestdemo.service.PostService;

@RestController
@RequestMapping("modelmapper/posts")
@CrossOrigin()
@Validated
public class PostModelMapperController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("")
	public List<PostMmDto> getAllPosts() {
		List<Post> posts =  postService.getAllPosts();
		List<PostMmDto> postMmts = new ArrayList<>();
		for (Post post : posts) {
			PostMmDto postMmDto = modelMapper.map(post, PostMmDto.class);
			postMmts.add(postMmDto);
		}
		return postMmts;
	}
		
	@GetMapping(value="/{id}")
	public PostMmDto getPost(@PathVariable("id") @Min(1) Integer id) {
		
		Post post =  postService.getPost(id);		
		PostMmDto postMmDto = modelMapper.map(post, PostMmDto.class);
		return postMmDto;
		
	}

}
