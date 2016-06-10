package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.IntegrationTestCase;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.fixtures.DrillTestFixtures;
import com.fprochazka.drill.model.exceptions.DrillCodeNotUniqueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
public class DrillFacadeTests extends IntegrationTestCase
{

	@Autowired
	private DrillFacade drillFacade;

	@Test(expected = DrillCodeNotUniqueException.class)
	public void testCreateDrillWithNoneUniqueIdThrowsException() throws DrillCodeNotUniqueException
	{
		drillFacade.createDrill("PB138", "Znacky");
	}


}
