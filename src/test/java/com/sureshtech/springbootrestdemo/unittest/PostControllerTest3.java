package com.sureshtech.springbootrestdemo.unittest;



import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.hamcrest.text.IsEmptyString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sureshtech.springbootrestdemo.entity.Comment;
import com.sureshtech.springbootrestdemo.entity.Post;
import com.sureshtech.springbootrestdemo.service.PostService;



@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest3 {
	
	Logger log = LoggerFactory.getLogger(PostControllerTest3.class);
	

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PostService postService;
	
	@BeforeEach
    void setUp() {

    }
	
	@Test
	public void testGetPost() throws Exception {

		int postId = 1;

		Post post = new Post();
		post.setId(postId);
		post.setContent("Content");
		post.setTitle("Title");

		when(postService.getPost(postId)).thenReturn(post);

		this.mockMvc.perform(get("/posts/{id}", postId))
														.andExpect(status().isOk()) // check HTTP status
														.andExpect(jsonPath("$.content").value("Content")) // check "content"
														.andExpect(jsonPath("$.title").value("Title")) // check "title"
														.andExpect(jsonPath("$.id", is(postId))); // check "id"

	}
	
	@Test
	public void testGetPostThrowsException() throws Exception {

		int postId = 100;

		when(postService.getPost(postId))
				.thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post Not found :" + postId));

		this.mockMvc.perform(get("/posts/{id}", postId))
														.andExpect(status().isBadRequest()) // check HTTP status
//														.andDo(handler -> log.info("response :" + handler.getResponse().getErrorMessage()))
														.andExpect(response -> assertTrue(response.getResolvedException() instanceof ResponseStatusException))
														.andExpect(response -> assertEquals("Post Not found :" + postId,response.getResponse().getErrorMessage()));

	}

	@Test
	public void testGetAllPosts() throws Exception {

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

		this.mockMvc.perform(get("/posts/")).andExpect(status().isOk()) // check HTTP status
//											.andDo(handler ->log.info(handler.getResponse().getContentAsString()))
											.andExpect(jsonPath("$.length()", is(2))) // check the size of post array in response
											.andExpect(jsonPath("$[0].content").value("Content")).andExpect(jsonPath("$[0].title").value("Title"))
											.andExpect(jsonPath("$[0].createdOn").value(IsNull.notNullValue()))
											.andExpect(jsonPath("$[0].updatedOn").value(IsNull.nullValue())).andExpect(jsonPath("$[0].id", is(1)))
											.andExpect(jsonPath("$[0].comments.*", hasSize(2))) // check the size of comments array in response
											.andExpect(jsonPath("$[0].comments[0].id", is(1)))
											.andExpect(jsonPath("$[0].comments[0].content", is("This is comment")))
											.andExpect(jsonPath("$[1].content").value("Content2")).andExpect(jsonPath("$[1].title").value("Title2"))
											.andExpect(jsonPath("$[1].id", is(2)))
											.andExpect(jsonPath("$[1].comments", is(IsEmptyString.emptyOrNullString()))) // check for empty or null  value
											.andExpect(jsonPath("$[1].comments", is(IsNull.nullValue()))) // check for null value
											.andExpect(jsonPath("$[0:].content", Matchers.containsInAnyOrder("Content2", "Content")))
											.andExpect(jsonPath("$[0:].content", Matchers.containsInRelativeOrder("Content", "Content2")));

	}

	@Test
	public void testCreatePost() throws Exception {

		Post post = new Post();
		post.setId(1);
		post.setContent("Content");
		post.setTitle("Title");

		when(postService.createPost(any((Post.class)))).thenReturn(post);

		this.mockMvc.perform(post("/posts/").contentType(MediaType.APPLICATION_JSON)
											.content(objectMapper.writeValueAsString(post)))
											.andExpect(status().isCreated())
											.andExpect(jsonPath("$.content").value("Content"))
											.andExpect(jsonPath("$.title").value("Title"))
											.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	public void testCreatePost2() throws Exception {

		Post post = new Post();
		post.setId(1);
		post.setContent("Content");
		post.setTitle("Title");

		when(postService.createPost(any((Post.class)))).thenReturn(post);

		MockHttpServletResponse response = this.mockMvc.perform(
				post("/posts/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(post)))
				.andReturn().getResponse();

		Post postObj = objectMapper.readValue(response.getContentAsString(), Post.class);

		assertEquals(post.getId(), postObj.getId());
		assertEquals(post.getContent(), postObj.getContent());
		assertEquals(post.getTitle(), postObj.getTitle());
		assertNotNull(post.getCreatedOn());
		assertNull(post.getUpdatedOn());

	}

	@Test
	public void testUpdatePost() throws Exception {

		Post post = new Post();
		post.setId(1);
		post.setContent("Content");
		post.setTitle("Title");

		when(postService.updatePost(any((Post.class)))).thenReturn(post);

		this.mockMvc.perform(put("/posts/{id}", 1).contentType(MediaType.APPLICATION_JSON)
												  .content(objectMapper.writeValueAsString(post)))
												  .andExpect(status().isOk())
												  .andExpect(jsonPath("$.content").value("Content"))
												  .andExpect(jsonPath("$.title").value("Title"))
												  .andExpect(jsonPath("$.id", is(1)))
												  .andDo(handler -> log.info(handler.getResponse().getContentAsString()));

	}

	@Test
	public void testUpdatePostThrowsException() throws Exception {

		int postId = 100;
		Post post = new Post();
		post.setId(postId);
		post.setContent("Content");
		post.setTitle("Title");

		when(postService.updatePost(any((Post.class))))
				.thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resource Not found"));

		this.mockMvc.perform(put("/posts/{id}", postId).contentType(MediaType.APPLICATION_JSON)
													   .content(objectMapper.writeValueAsString(post)))
													   .andExpect(status().isBadRequest())
													   .andExpect(response -> assertTrue(response.getResolvedException() instanceof ResponseStatusException))
													   .andExpect(response -> assertEquals("Resource Not found", response.getResponse().getErrorMessage()))
													   .andDo(handler -> log.info(handler.getResponse().getContentAsString()));

	}

	@Test
	public void testPostDelete() throws Exception {

		int postId = 100;
		Post post = new Post();
		post.setId(postId);
		post.setContent("Content");
		post.setTitle("Title");

		this.mockMvc.perform(delete("/posts/{id}", postId)).andExpect(status().isOk());

	}	
	


}
