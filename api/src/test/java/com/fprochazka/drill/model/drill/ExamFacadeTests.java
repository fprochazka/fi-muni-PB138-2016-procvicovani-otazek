package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.IntegrationTestCase;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.fixtures.DrillTestFixtures;
import com.fprochazka.drill.fixtures.StudentTestFixtures;
import com.fprochazka.drill.model.exam.ExamFacade;
import com.fprochazka.drill.model.exceptions.NotFoundException;
import com.fprochazka.drill.model.exceptions.NotUniqueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Michaela Bamburová on 11.06.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
public class ExamFacadeTests extends IntegrationTestCase
{
	@Autowired
	public ExamFacade examFacade;

	@Test(expected = NotFoundException.class)
	public void testCreateExamWithNoneExistingDrill() throws NotFoundException, NotUniqueException
	{
		Drill drill = new Drill("code", "name");
		examFacade.createExam(drill.getId(), StudentTestFixtures.student123456.getId());
	}

	@Test(expected = NotUniqueException.class)
	public void testCreateExistingExamThrowsException() throws NotFoundException, NotUniqueException
	{
		examFacade.createExam(DrillTestFixtures.drillPB138.getId(), StudentTestFixtures.student123456.getId());
	}

	@Test
	public void testCreateValidExam() throws NotFoundException, NotUniqueException
	{
		examFacade.createExam(DrillTestFixtures.drillMB104.getId(), StudentTestFixtures.student123456.getId());
	}
}
