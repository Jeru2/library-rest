package com.mindtree.libraryspring2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.libraryspring2.controller.BookController;
import com.mindtree.libraryspring2.entity.Author;
import com.mindtree.libraryspring2.entity.Book;
import com.mindtree.libraryspring2.repository.BookRepository;
import com.mindtree.libraryspring2.service.serviceImpl.BookServiceImpl;

@SpringBootTest
public class BookControllerTest
{	
	MockMvc mockMvc;
	
	@MockBean
	BookRepository bookRepo;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Mock
	Book book;
	BookServiceImpl bookService;
	
	@InjectMocks
	BookController bookController;
	
	@BeforeEach
	public void setup()
	{
		List<Author> authorList = new ArrayList<Author>();
		Author ath = new Author("Sun Ra", "1944");
		authorList.add(ath);
		book = new Book("The Art of War", 10, "Mandarin", "Military strategy", authorList, 49);
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	   protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
	   protected <T> T mapFromJson(String json, Class<T> classExample)
	      throws JsonParseException, JsonMappingException, IOException {
	      
	      ObjectMapper objectMapper = new ObjectMapper();
	      objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	      return objectMapper.readValue(json, classExample);
	   }
	
	@Test
	public void PostMappingOfProduct() throws Exception{		
	   	String uri = "http://localhost:8082/book/addBook";
	   	String inputJson = mapToJson(book);
	   	RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
	   			.content(inputJson).contentType(MediaType.APPLICATION_JSON);
	   	MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
	   	MockHttpServletResponse response = mvcResult.getResponse();
	   	assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	   	String outputJson = response.getContentAsString();
	   	assertEquals("{\"code\":\"201\",\"status\":\"CREATED\"}", outputJson);
	}
	
	@Test
	public void getProductsList() throws Exception {
	   String uri = "http://localhost:8082/book/books";
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();	   
	   int returnedStatus = mvcResult.getResponse().getStatus();
	   assertEquals(200, returnedStatus);
	   String content = mvcResult.getResponse().getContentAsString();
	   Book[] bookList = mapFromJson(content, Book[].class);
	   assertTrue(bookList.length > 0);
	}
}
