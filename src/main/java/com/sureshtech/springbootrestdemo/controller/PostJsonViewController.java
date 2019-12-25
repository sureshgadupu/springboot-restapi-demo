package com.sureshtech.springbootrestdemo.controller;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.sureshtech.springbootrestdemo.entity.Post;
import com.sureshtech.springbootrestdemo.service.PostService;
import com.sureshtech.springbootrestdemo.views.PostView;

@RestController
@RequestMapping("/posts")
@CrossOrigin()
@Validated
public class PostJsonViewController {
	
	@Autowired
	private PostService postService;
	
	@JsonView(PostView.ExternalView.class)
	@GetMapping(value="external/{id}")
	public Post getPostForExternal(@PathVariable("id") @Min(1) Integer id) {
		return postService.getPost(id);
	}
	
	@JsonView(PostView.InternalView.class)
	@GetMapping(value="internal/{id}")
	public Post getPostForInternal(@PathVariable("id") @Min(1) Integer id) {
		return postService.getPost(id);
	}
	
	@JsonView({PostView.PartnerView.class})
	@GetMapping(value="partner/{id}")
	public Post getPostForPartners(@PathVariable("id") @Min(1) Integer id) {
		return postService.getPost(id);
	}

}
