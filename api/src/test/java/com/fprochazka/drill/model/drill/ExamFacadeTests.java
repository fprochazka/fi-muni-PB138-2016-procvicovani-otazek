package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.IntegrationTestCase;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.fixtures.DrillTestFixtures;
import com.fprochazka.drill.fixtures.StudentTestFixtures;
import com.fprochazka.drill.model.exam.ExamFacade;
import com.fprochazka.drill.model.exam.ExamNotUniqueException;
import com.fprochazka.drill.model.student.StudentNotFoundException;
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

	@Test(expected = DrillNotFoundException.class)
	public void testCreateExamWithNoneExistingDrill() throws ExamNotUniqueException, DrillNotFoundException, StudentNotFoundException
	{
		Drill drill = new Drill("code", "name");
		examFacade.createExam(drill.getId(), StudentTestFixtures.student123456.getId());
	}

	@Test(expected = ExamNotUniqueException.class)
	public void testCreateExistingExamThrowsException() throws ExamNotUniqueException, DrillNotFoundException, StudentNotFoundException
	{
		examFacade.createExam(DrillTestFixtures.drillPB138.getId(), StudentTestFixtures.student123456.getId());
	}

	@Test
	public void testCreateValidExam() throws ExamNotUniqueException, DrillNotFoundException, StudentNotFoundException
	{
		examFacade.createExam(DrillTestFixtures.drillMB104.getId(), StudentTestFixtures.student123456.getId());
	}
}
