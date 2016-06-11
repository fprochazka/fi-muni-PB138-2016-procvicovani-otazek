package com.fprochazka.drill.fixtures;

import com.mongodb.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class FixturesInstaller
{

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserTestFixtures userFixtures;

	@Autowired
	private DrillTestFixtures drillFixtures;

	@Autowired
	private ExamTestFixtures examFixtures;

	public void install()
	{
		clearDatabase();

		userFixtures.install();
		drillFixtures.install();
		examFixtures.install();
	}

	private void clearDatabase()
	{
		DB db = mongoTemplate.getDb();
		db.getCollectionNames()
			.stream()
			.filter(collectionName -> !collectionName.startsWith("system."))
			.forEach(collectionName -> {
				mongoTemplate.remove(new Query(), collectionName);
			});
	}

}
