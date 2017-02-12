package com.coffemachine.services;

import java.util.List;

import com.coffemachine.module.User;

public interface UserService {
	
	public List<User> getAllUsers();
	
	public User getUser(Long id);
	
	public User getByEmail(String email);
	
	public void addUser(User user);
	
	public void deleteUser(Long id);

	
	public void updateUser(User user);

}
