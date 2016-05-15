package com.fprochazka.drill.config;


/*
@Configuration
@PropertySource("classpath:mongodb.properties")
@EnableMongoRepositories(basePackages = { "com.fprochazka.drill" })
public class MongoDBConfig extends AbstractMongoConfiguration
{

    @Value("${mongodb.db}")
    private String databaseName;

    @Value("${mongodb.url}")
    private String url;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoClientURI mci = new MongoClientURI(url);
        return new MongoClient(mci);
    }

}
*/
