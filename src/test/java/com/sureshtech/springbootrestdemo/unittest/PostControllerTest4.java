package com.sureshtech.springbootrestdemo.unittest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import com.sureshtech.springbootrestdemo.entity.Comment;
import com.sureshtech.springbootrestdemo.entity.Post;
import com.sureshtech.springbootrestdemo.service.PostService;


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)

public class PostControllerTest4 {

	Logger log = LoggerFactory.getLogger(PostControllerTest4.class);
	
	@MockBean
	private PostService postService;
	
	@Autowired
	private TestRestTemplate testRestTemplate ;
	
	private static final String ROOT_URL = "http://localhost:8085";	
	
	
	
	@Test
	public void testAllPosts() {
		
		Post post = new Post();
		post.setId(1);
		post.setContent("Content");
		post.setTitle("Title");

		Comment comment = new Comment();
		comment.setId(1);
		comment.setContent("This is comment");
		comment.setEmail("test@test@email.com");

		Comment comment2 = new Comment();
		comment2.setId(2);
		comment2.setContent("This is comment-2");
		comment2.setEmail("test2@test@email.com");

		List<Comment> commentsList = new ArrayList<>();
		commentsList.add(comment);
		commentsList.add(comment2);
		post.setComments(commentsList);

		Post post2 = new Post();
		post2.setId(2);
		post2.setContent("Content2");
		post2.setTitle("Title2");

		List<Post> allPosts = new ArrayList<>();
		allPosts.add(post);
		allPosts.add(post2);
		
		when(postService.getAllPosts()).thenReturn(allPosts);
		
		ResponseEntity<Post[]> responseEntity = testRestTemplate.getForEntity(ROOT_URL+"/posts", Post[].class);
		
		List<Post> posts = Arrays.asList(responseEntity.getBody());
		
//		for (Post p : posts) {
//			log.info("Post = "+ p.getId() + " : "+ p.getTitle());
//		}
		assertNotNull(posts);
		assertArrayEquals("", allPosts.toArray(), posts.toArray());
		assertEquals( allPosts.size(), posts.size());
		
	}
	
	@Test
	public void testGetPostById() {
		
		int postId = 1;
		
		Post post = new Post();
		post.setId(postId);
		post.setContent("Content");
		post.setTitle("Title");
		
		when(postService.getPost(postId)).thenReturn(post);
		
		ResponseEntity<Post> responseEntity = testRestTemplate.getForEntity(ROOT_URL+"/posts/{id}", Post.class,postId);
		
		Post p = (Post) responseEntity.getBody();
		
		assertEquals(postId, p.getId().intValue());
		assertEquals(post.getTitle(), p.getTitle());
	}
	
	@Test
	public void testGetPostByIdThrowsException() {
		
		int postId = 199;
		
		Post post = new Post();
		post.setId(postId);
		post.setContent("Content");
		post.setTitle("Title");
		
		when(postService.getPost(postId)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resource Not found"));
		
		ResponseEntity<Post> responseEntity = testRestTemplate.getForEntity(ROOT_URL+"/posts/{id}", Post.class,postId);
		
		assertNull( responseEntity.getBody().getContent());	
		assertEquals(400,responseEntity.getStatusCodeValue());
		
		
	}
	
	@Test	
	public void testCreatePost()
	{
		int postId = 1;
		
		Post post = new Post();
		post.setId(postId);
		post.setTitle("Testing title");
		post.setContent("Testing content!!");
		
		when(postService.createPost(post)).thenReturn(post);
		
		ResponseEntity<Post> postResponse = testRestTemplate.postForEntity(ROOT_URL+"/posts", post, Post.class);		
		
		Post p = (Post) postResponse.getBody();
		
		assertNotNull(postResponse.getBody());
		
		assertEquals(post.getTitle(),p.getTitle());
	}
	

	
	
}
