package com.fprochazka.drill.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.UUID;

/**
 * Created by Michaela Bamburová on 21.05.2016.
 */
public interface AddressRepo  extends MongoRepository<Address, UUID> {

	@Query("{'city' : ?0}")
	Iterable<Address> searchByCity(String city);
}
