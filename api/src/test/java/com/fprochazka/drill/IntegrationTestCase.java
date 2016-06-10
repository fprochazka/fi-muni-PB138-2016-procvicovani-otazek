package com.fprochazka.drill;

import com.fprochazka.drill.fixtures.FixturesInstaller;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class IntegrationTestCase
{

	@Autowired
	private FixturesInstaller fixturesInstaller;

	@Before
	public void loadFixtures()
	{
		fixturesInstaller.install();
	}

}
