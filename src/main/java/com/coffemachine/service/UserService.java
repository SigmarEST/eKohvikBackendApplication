package com.coffemachine.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffemachine.module.User;
import com.coffemachine.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	public User getUser(Long id){
		return userRepository.findOne(id);
	}
	
	public User getByEmail(String email){
		return userRepository.findByemail(email);
	}
	
	public void addUser(User user){
		userRepository.save(user);
	}
	
	public void deleteUser(Long id){
		userRepository.delete(id);
	}

	
	public void updateUser(User user){
		userRepository.save(user);
	}
}
