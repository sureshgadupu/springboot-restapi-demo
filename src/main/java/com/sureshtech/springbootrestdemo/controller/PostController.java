package com.sureshtech.springbootrestdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.sureshtech.springbootrestdemo.exception.ResourceNotFoundException;
import com.sureshtech.springbootrestdemo.service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin()
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	
	@ResponseStatus(HttpStatus.CREATED) // send HTTP 201 instead of 200 as new object created
	@PostMapping("")
	public Post createPost(@RequestBody Post post)
	{
		return postService.createPost(post);
	}
	
	@GetMapping("")
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}
	
	
	
	@GetMapping(value="/{id}")
	public Post getPost(@PathVariable("id") Integer id) {
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
		
//		Post post = postService.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Resource Not found :"+postId) );
//		
//		comment.setPost(post);
//		post.getComments().add(comment);
//		postService.save(post);
		 
		return postService.createPostComments(postId,comment);
		
	}
	
	@DeleteMapping("/{post_id}/comments/{comment_id}")
	public void deletePostComments(@PathVariable("post_id") Integer postId, @PathVariable("comment_id") Integer commentId) {
		
//		postService.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Post Not found :"+postId) );
//		commentRepository.findById(commentId).orElseThrow( () -> new ResourceNotFoundException("Comment Not found :"+commentId) );
//		commentRepository.deleteById(commentId);
		postService.deletePostComments(postId, commentId);
		
		
	}


}
