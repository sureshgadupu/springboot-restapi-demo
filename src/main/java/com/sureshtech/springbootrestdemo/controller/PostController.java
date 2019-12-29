package com.sureshtech.springbootrestdemo.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sureshtech.springbootrestdemo.entity.Comment;
import com.sureshtech.springbootrestdemo.entity.Post;
import com.sureshtech.springbootrestdemo.service.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Blog post management service", value = "Post Controller")
@RestController
@RequestMapping("/posts")
@CrossOrigin()
@Validated
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	
	@ResponseStatus(HttpStatus.CREATED) // send HTTP 201 instead of 200 as new object created
	@PostMapping("")
	public Post createPost(@RequestBody Post post)
	{
		return postService.createPost(post);
	}
	
	@ApiOperation(value = "getAllPosts",notes = "Get list of all posts")
	@GetMapping("")
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}
	
	
	@ApiOperation(value = "getPost",notes = "Retrieve Post based on id")
	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Post getPost(@ApiParam(required = true,value = "id" , example = "10") @PathVariable("id") @Min(1) Integer id) {
		return postService.getPost(id);
	}
	
	@PutMapping(value="/{id}")
	public Post updatePost(Post post){
		
		
		return postService.updatePost(post);
		
	}
	
	@DeleteMapping(value="/{id}")
	public @ResponseBody void deletePost(@PathVariable("id") Integer id){
		postService.deletePost(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED) // send HTTP 201 instead of 200 as new object created
	@PostMapping("/{id}/comments")
	public Post createPostComments(@PathVariable("id") Integer postId, @RequestBody Comment comment) {
		return postService.createPostComments(postId,comment);
		
	}
	
	@DeleteMapping("/{post_id}/comments/{comment_id}")
	public void deletePostComments(@PathVariable("post_id") Integer postId, @PathVariable("comment_id") Integer commentId) {
		postService.deletePostComments(postId, commentId);
		
		
	}


}
