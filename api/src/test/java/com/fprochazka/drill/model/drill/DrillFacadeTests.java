package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.IntegrationTestCase;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.model.exceptions.NotUniqueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebIntegrationTest(randomPort=true)
public class DrillFacadeTests extends IntegrationTestCase
{

	@Autowired
	private DrillFacade drillFacade;

	@Test(expected = NotUniqueException.class)
	public void testCreateDrillWithNoneUniqueIdThrowsException() throws NotUniqueException
	{
		drillFacade.createDrill("PB138", "Znacky");
	}

}
