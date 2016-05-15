package com.fprochazka.drill.model;

/**
 * Created by Michaela Bamburová on 21.05.2016.
 */

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PersonRepo extends MongoRepository<Person, String>
{
	@Query("{'name' : ?0}")
	Person searchByName(String personName);
	//List<Address> findByPerson(Person person);

	@Query("{'age' : 25}")
	Iterable<Person> searchByAge();

	@Query("{'addresses' : { $elemMatch: { address: { $gt: '221b Baker Street' }, city: { $gt: 'London NW1' } }}, {'addresses.$': 1 }")
	Iterable<Address> getAllAddressesInPerson();
}
