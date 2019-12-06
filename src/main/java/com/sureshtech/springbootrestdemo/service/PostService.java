package com.sureshtech.springbootrestdemo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

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
		try {
			return postRepository.save(post);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Exception while creating Post",e);
		}
	}
	
	
	public Post getPost(Integer id) {
		return postRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Resource Not found") );
	}
	
	
	public Post updatePost(Post post){
		
		postRepository.findById(post.getId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Resource Not found") );
		return postRepository.save(post);
		
	}
	
	
	public @ResponseBody void deletePost(Integer id){
		
		postRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Post Not found :"+id) );		
		postRepository.deleteById(id);
		
	}
	
	
	public Post createPostComments(Integer postId, Comment comment) {
		
		Post post = postRepository.findById(postId).orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Post Not found :"+postId) );
		
		comment.setPost(post);
		post.getComments().add(comment);
		postRepository.save(post);
		 
		return post;
		
	}
	
	
	public void deletePostComments(Integer postId,  Integer commentId) {
		
		postRepository.findById(postId).orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Post Not found :"+postId) ); //new ResourceNotFoundException("Post Not found :"+postId)
		commentRepository.findById(commentId).orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Comment Not found :"+commentId) );
		commentRepository.deleteById(commentId);
		
		
	}
}
