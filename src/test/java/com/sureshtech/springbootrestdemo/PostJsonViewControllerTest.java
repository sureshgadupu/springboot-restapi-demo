package com.sureshtech.springbootrestdemo;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.sureshtech.springbootrestdemo.controller.PostJsonViewController;
import com.sureshtech.springbootrestdemo.entity.Post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class PostJsonViewControllerTest {
	
	Logger log = LoggerFactory.getLogger(PostJsonViewControllerTest.class);
	
	@Autowired
	PostJsonViewController postJsonViewController;
	

	RestTemplate restTemplate = new RestTemplate();
	
	static String URL = "http://localhost:8080";
	
	@org.junit.jupiter.api.Test
	void contextLoads() {
		
	}
	
	@Test	
	public void testGetPostForExternal() {
		
		ResponseEntity<Post> response = 	restTemplate.getForEntity(URL+"posts/external/10", Post.class);
		
		Post post = (Post) response.getBody();
		assertNotNull(post.getTitle());
		assertNotNull(post.getContent());
		assertNotNull(post.getId());
		
		assertNull(post.getCreatedOn());
		
		assertEquals(0,post.getComments().size());
		
	}
	
	@Test	
	public void testGetPostForInternal() {
		
		ResponseEntity<Post> response = 	restTemplate.getForEntity(URL+"posts/internal/10", Post.class);
		
		Post post = (Post) response.getBody();
		assertNotNull(post.getTitle());
		assertNotNull(post.getContent());
		assertNotNull(post.getId());
		assertNotNull(post.getCreatedOn());
		
		
	}
	
	
	@Test	
	public void testGetPostForPartner() {
		
		ResponseEntity<Post> response = 	restTemplate.getForEntity(URL+"posts/partner/10", Post.class);
		
		Post post = (Post) response.getBody();
		assertNotNull(post.getTitle());
		assertNotNull(post.getContent());
		assertNotNull(post.getId());
	
		
	}

}
