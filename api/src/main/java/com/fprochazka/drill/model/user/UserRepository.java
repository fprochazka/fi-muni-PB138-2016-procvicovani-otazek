package com.fprochazka.drill.model.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>
{

	User getUserById(UUID userId);

	User getUserByUco(int uco);

	User getUserByEmail(String email);

}
