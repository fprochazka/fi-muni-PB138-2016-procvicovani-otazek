package com.fprochazka.drill.api.user;

import com.fprochazka.drill.model.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserResponseFactory
{

	public UserResponse createUserResponse(User user)
	{
		return new UserResponse(user.getId(), user.getUco(), user.getEmail());
	}

}
