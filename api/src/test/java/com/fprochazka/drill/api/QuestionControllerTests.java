package com.fprochazka.drill.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fprochazka.drill.Application;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.question.Question;
import com.fprochazka.drill.model.drill.question.QuestionRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by viki on 11.06.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebIntegrationTest
public class QuestionControllerTests
{
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private QuestionRepository repository;

	private RestTemplate restTemplate = new TestRestTemplate();

	private Drill drill;
	private Question question1;
	private Question question2;
	private Question question3;

	@Test
	public void testCreateQuestion() throws JsonProcessingException
	{
		Map< String, Object > requestBody = new HashMap<>();
		Map< String, Object > answers = new HashMap<>();

		

		requestBody.put( "title", "Kolko je hodin?" );
		//requestBody.put( "name", "Moderni znackovaci jazyky a jejich aplikace" );

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON );

		HttpEntity requestEntity = new HttpEntity<String>( OBJECT_MAPPER.writeValueAsString( requestBody ), requestHeaders );

		Map<String, Object> apiResponse =
			restTemplate.postForObject( "http://localhost:8080/question",
			requestEntity, Map.class, Collections.EMPTY_MAP );
		assertNotNull( apiResponse );

	}
}
