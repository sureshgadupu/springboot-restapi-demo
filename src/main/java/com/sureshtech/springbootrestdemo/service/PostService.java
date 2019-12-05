package com.sureshtech.springbootrestdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sureshtech.springbootrestdemo.entity.Comment;
import com.sureshtech.springbootrestdemo.entity.Post;
import com.sureshtech.springbootrestdemo.exception.ResourceNotFoundException;
import com.sureshtech.springbootrestdemo.repository.CommentRepository;
import com.sureshtech.springbootrestdemo.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}
	
	public Post createPost( Post post)
	{
		return postRepository.save(post);
	}
	
	
	public Post getPost(Integer id) {
		return postRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Resource Not found") );
	}
	
	
	public Post updatePost(Post post){
		
		postRepository.findById(post.getId()).orElseThrow( () -> new ResourceNotFoundException("Resource Not found") );
		return postRepository.save(post);
		
	}
	
	
	public @ResponseBody void deletePost(Integer id){
		
		postRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Resource Not found") );		
		postRepository.deleteById(id);
		
	}
	
	
	public Post createPostComments(Integer postId, Comment comment) {
		
		Post post = postRepository.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Resource Not found :"+postId) );
		
		comment.setPost(post);
		post.getComments().add(comment);
		postRepository.save(post);
		 
		return post;
		
	}
	
	
	public void deletePostComments(Integer postId,  Integer commentId) {
		
		postRepository.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Post Not found :"+postId) );
		commentRepository.findById(commentId).orElseThrow( () -> new ResourceNotFoundException("Comment Not found :"+commentId) );
		commentRepository.deleteById(commentId);
		
		
	}
}
