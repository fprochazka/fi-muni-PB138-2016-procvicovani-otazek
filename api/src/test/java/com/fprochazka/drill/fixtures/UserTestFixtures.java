package com.fprochazka.drill.fixtures;

import com.fprochazka.drill.model.user.User;
import com.fprochazka.drill.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTestFixtures
{

	public static User user123456;

	@Autowired
	private UserRepository userRepository;

	public void install()
	{
		user123456 = new User(123456, "mail@mail.com", "hash");
		userRepository.save(user123456);
	}
}
