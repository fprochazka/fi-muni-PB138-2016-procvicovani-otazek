package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.IntegrationTestCase;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.fixtures.DrillTestFixtures;
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
public class DrillRepositoryTests extends IntegrationTestCase
{

	@Autowired
	private DrillRepository drillRepository;

	@Test
	public void testFindDrillByCode()
	{
		assertEquals(DrillTestFixtures.drillMB104, drillRepository.getDrillByCode(DrillTestFixtures.drillMB104.getCode()));
	}

	@Test
	public void testFindDrillById()
	{
		assertEquals(DrillTestFixtures.drillPB138, drillRepository.getDrillById(DrillTestFixtures.drillPB138.getId()));
	}



}
