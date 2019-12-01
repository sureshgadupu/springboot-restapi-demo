package com.sureshtech.springbootrestdemo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.sureshtech.springbootrestdemo.entity.Comment;
import com.sureshtech.springbootrestdemo.entity.Post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class SpringbootRestApiDemoApplicationTests {

	
	private RestTemplate restTemplate = new RestTemplate();
	
	private static final String ROOT_URL = "http://localhost:8080";
	
	@Test
	void contextLoads() {
		
	}
	
	
	
	@Test
	public void testAllPosts() {
		
		ResponseEntity<Post[]> responseEntity = restTemplate.getForEntity(ROOT_URL+"/posts", Post[].class);
		List<Post> posts = Arrays.asList(responseEntity.getBody());
		for (Post post : posts) {
			System.out.println("Post = "+ post.getId() + " : "+ post.getTitle());
		}
		assertNotNull(posts);
		
	}
	
	@Test
	public void testGetPostById() {
		ResponseEntity<Post> responseEntity = restTemplate.getForEntity(ROOT_URL+"/posts/10", Post.class);
		Post post = (Post) responseEntity.getBody();
		assertEquals(10,  post.getId());
	}
	
	@Test	
	public void testCreatePost()
	{
		Post post = new Post();
		post.setTitle("Testing create Post method");
		post.setContent("Testing create Post method content!!");
		
		ResponseEntity<Post> postResponse =
		restTemplate.postForEntity(ROOT_URL+"/posts", post, Post.class);		
		assertNotNull(postResponse);		
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testCreateCommentForPost() {
		
		Comment comment = new Comment();
		
		comment.setContent("New Comment");
		comment.setCreatedOn(new Date());
		comment.setEmail("abc@xyz.com");
		comment.setName("Suresh");
		
		
		ResponseEntity<Post>  post = restTemplate.postForEntity(ROOT_URL+"/posts/10/comments",comment,  Post.class);
		System.out.println(" post :"+ post.getBody().getComments().size());
		for (Comment commentex  : post.getBody().getComments()) {
			System.out.println(commentex.getId() + " : "+ commentex.getContent());
		}
		assertNotNull(post.getBody().getComments());
		//assertNotNull(post.getBody().getComments());
	}
	
	@Test
	public void testDeletePost() {
		
		int postId = 20;
		ResponseEntity<Post> responseEntiry =  restTemplate.getForEntity(ROOT_URL+"/posts/"+postId, Post.class);
		assertNotNull(responseEntiry);
		
		//restTemplate.
		System.out.print("0");
		restTemplate.delete(ROOT_URL+"posts/"+postId);
		System.out.print("1");
		try {
			responseEntiry =  restTemplate.getForEntity(ROOT_URL+"/posts/"+postId, Post.class);
			System.out.print("2");
		}catch(Exception e) {
			System.out.print(e);
		}
		
		
		
	}

}