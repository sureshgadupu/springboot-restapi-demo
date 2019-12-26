package com.sureshtech.springbootrestdemo;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sureshtech.springbootrestdemo.controller.PostJsonViewController;
import com.sureshtech.springbootrestdemo.entity.Post;
import com.sureshtech.springbootrestdemo.views.PostView;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

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
	public void testGetPostForExternal() throws JsonProcessingException {
		
		ResponseEntity<Post> response = 	restTemplate.getForEntity(URL+"posts/external/10", Post.class);
		
		Post post = (Post) response.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String result = mapper
			      .writerWithView(PostView.ExternalView.class)
			      .writeValueAsString(post);
		
		//assertThat(result, containsString("book"));
		assertThat(result, containsString("id"));
		assertThat(result, containsString("title"));
		assertThat(result, containsString("content"));
		assertThat(result, not(containsString("createdOn")));
		

		
	}
	
	

	@Test	
	public void testGetPostForInternal() throws JsonProcessingException {
		
		ResponseEntity<Post> response = 	restTemplate.getForEntity(URL+"posts/internal/10", Post.class);
		
		Post post = (Post) response.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writerWithView(PostView.InternalView.class).writeValueAsString(post);
		post = null;
		post = mapper.readerWithView(PostView.InternalView.class).forType(Post.class).readValue(result);
		assertThat(result, containsString("id"));
		assertThat(result, containsString("title"));
		assertThat(result, containsString("content"));
		assertThat(result, containsString("updatedOn"));
		
		assertThat(result, (containsString("createdOn")));
		
		
	}
	
	
	@Test	
	public void testGetPostForPartner() throws JsonProcessingException {
		
		ResponseEntity<Post> response = 	restTemplate.getForEntity(URL+"posts/partner/10", Post.class);
		
		Post post = (Post) response.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String result = mapper.writerWithView(PostView.PartnerView.class).writeValueAsString(post);
				
		post = null;
		
		post = mapper.readerWithView(PostView.PartnerView.class).forType(Post.class).readValue(result);
		
		assertThat(result, containsString("id"));
		assertThat(result, containsString("title"));
		assertThat(result, containsString("content"));
		assertThat(result, containsString("updatedOn"));
		assertThat(result, not(containsString("createdOn")));
		
		
		
	}

}
