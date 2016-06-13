package com.fprochazka.drill.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fprochazka.drill.Application;
import com.fprochazka.drill.IntegrationTestCase;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.fixtures.DrillTestFixtures;
import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.DrillCodeNotUniqueException;
import com.fprochazka.drill.model.drill.DrillFacade;
import com.fprochazka.drill.model.drill.DrillRepository;
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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebIntegrationTest(randomPort=true)
public class DrillControllerTests extends IntegrationTestCase
{
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private DrillFacade facade;
	@Autowired
	private TestRequestFactory factory;

	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testCreateDrill() throws JsonProcessingException
	{
		Drill drill = new Drill( "MB101", "Linearni modely" );

		Map< String, Object > requestBody = factory.createCreateDrillRequest( drill );

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON );

		HttpEntity requestEntity = new HttpEntity<String>( OBJECT_MAPPER.writeValueAsString( requestBody ), requestHeaders );


		Map<String, Object> apiResponse =
			restTemplate.postForObject( "http://localhost:8080/drill",
				requestEntity, Map.class, Collections.EMPTY_MAP );

		System.out.println( apiResponse.toString() );
		assertNotNull( apiResponse );

		Drill responseDrill = (Drill) apiResponse.get( "drill" );/*
		assertEquals( responseDrill.getId(), drill.getId() );
		assertEquals( responseDrill.getCode(), drill.getCode() );
		assertEquals( responseDrill.getName(), responseDrill.getName() );*/
	}
}
