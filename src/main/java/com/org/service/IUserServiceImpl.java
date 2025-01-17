package com.org.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.entities.User;
import com.org.exceptions.InvalidUsernameException;
import com.org.repository.IUserRepository;

@Service
@Transactional
public class IUserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository User_Repository;

	/*
	 * Add User
	 */
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return User_Repository.save(user);
	}


	/*
	 * Get User details
	 */
	public User getUserDetailsByUserName(String username) throws InvalidUsernameException {
		return User_Repository.findById(username).orElseThrow(() -> new InvalidUsernameException("username not found"));

	}


	/*
	 * Delete User
	 */
	@Override
	public void deleteUser(String username) throws InvalidUsernameException {
		Optional<User> findUserByusername = User_Repository.findById(username);
		if (findUserByusername.isPresent()) {
			User_Repository.deleteById(username);
		} else
			throw new InvalidUsernameException("Username Not Found to Delete");
	}

	/*
	 * Update User Password
	 */
	@Override
	public int updatePassword(String username, String newPassword) throws InvalidUsernameException {
		Optional<User> findBusOperatorByUsername = User_Repository.findById(username);
		if (findBusOperatorByUsername.isPresent()) {
			return User_Repository.updatePassoword(username, newPassword);
		} else
			throw new InvalidUsernameException("User username not exists!!");

	}

}
