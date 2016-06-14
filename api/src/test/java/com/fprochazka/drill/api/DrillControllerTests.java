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
import org.mockito.internal.util.collections.Iterables;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebIntegrationTest(randomPort=true)
public class DrillControllerTests extends IntegrationTestCase
{
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private DrillFacade facade;
	@Autowired
	private DrillRepository repository;
	@Autowired
	private TestRequestFactory factory;

	private MockMvc mockMvc;

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.build();
	}


	@Test
	public void testCreateDrill() throws Exception
	{
		Drill drill = new Drill( "MB101", "Linearni modely" );

		Map< String, Object > requestBody = factory.createCreateDrillRequest( drill );

		mockMvc.perform( post("/drill")
				.contentType( MediaType.APPLICATION_JSON )
				.content( OBJECT_MAPPER.writeValueAsString( requestBody )))
			.andExpect( jsonPath( "code", is( drill.getCode() ) ))
			.andExpect( jsonPath( "name", is( drill.getName() ) ))
			.andExpect( status().isOk() );
	}

	@Test
	public void testGetDrill() throws Exception
	{
		Drill drill = repository.getDrillByCode("MB104");

		mockMvc.perform( get("/drill/{id}", drill.getId().toString() ) )
			.andExpect( jsonPath( "code", is( drill.getCode() ) ))
			.andExpect( jsonPath( "name", is( drill.getName() ) ))
			.andExpect( status().isOk() );
	}

	@Test
	public void testGetAllDrills() throws Exception
	{
		// get all drills from db
		Iterable< Drill > drills = repository.findAll();

		// map iterables for easier searching
		HashMap< UUID, Drill > drillsMapped = new HashMap<>();
		for ( Drill drill: drills )
		{
			drillsMapped.put( drill.getId(), drill );
		}

		// get response from server
		ResultActions result = mockMvc.perform( get("/drill") ).andExpect( status().isOk() );
		String jsonResponse = result.andReturn().getResponse().getContentAsString();

		// parse response to get returned drills
		List< HashMap<String,Object> > drillsParsed =
			(ArrayList) new ObjectMapper().readValue( jsonResponse, HashMap.class).get("drills");

		// for each parsed drill there must be equivalent in mapped drills. one to one
		int count = drillsMapped.size();
		for ( int i = 0; i < count; i++ )
		{
			UUID id = UUID.fromString( (String) drillsParsed.get( i ).get("id") );
			result.andExpect( jsonPath("$.drills[" + i + "].code",  is( drillsMapped.get( id ).getCode() ) ) );
			result.andExpect( jsonPath("$.drills[" + i + "].name",  is( drillsMapped.get( id ).getName() ) ) );

			// to prevent being matched twice
			drillsMapped.remove( id );
		}
	}
}
