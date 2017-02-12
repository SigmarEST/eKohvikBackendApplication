package com.coffemachine.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coffemachine.module.User;
import com.coffemachine.services.UserService;


@RestController
@RequestMapping("/coffemachine")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping("/users/{id}")
	public User getUser(@PathVariable Long id){
		return userService.getUser(id);
	}
	
	@RequestMapping("/users/email/{email:.+}")
	public User getUserByEmail(@PathVariable String email){
		System.out.println(email);
		return userService.getByEmail(email);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/users/add")
	public void addUser(@RequestBody User user){
		userService.addUser(user);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/delete/{id}")
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}
	
	@RequestMapping(method  = RequestMethod.PUT, value = "/users/update")
	public void userUpdate(@RequestBody User user){
		userService.updateUser(user);
	}


}
