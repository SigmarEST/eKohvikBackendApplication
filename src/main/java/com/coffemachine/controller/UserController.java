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
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping("/{id}")
	public User getUserById(@PathVariable Long id){
		return userService.getUser(id);
	}
	
	@RequestMapping("/email/{email:.+}")
	public User getUserByEmail(@PathVariable String email){
		System.out.println(email);
		return userService.getByEmail(email);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public void addUser(@RequestBody User user){
		userService.addUser(user);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}
	  
	@RequestMapping(method  = RequestMethod.PUT, value = "/")
	public void userUpdate(@RequestBody User user){
		userService.updateUser(user);
	}
	  
  /*@RequestMapping(value = "/users/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        System.out.println("Updating user " + id);
         
        User currentUser = userService.getUser(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setCards(user.getCards());
        currentUser.setBalance(user.getBalance());
         
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }*/


}
