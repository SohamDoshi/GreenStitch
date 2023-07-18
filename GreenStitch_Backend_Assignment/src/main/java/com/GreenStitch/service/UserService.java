package com.GreenStitch.service;

import com.GreenStitch.exceptions.UserException;
import com.GreenStitch.model.User;

public interface UserService {

	public User registerUser(User user) throws UserException;
	public User loginUser()  throws UserException;
}
