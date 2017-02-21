package com.coffemachine.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.coffemachine.module.User;
import com.coffemachine.services.UserService;


@RestController
//@RequestMapping("/coffemachine")
public class UserController {
	
	@Autowired
	UserService userService;
	
	//@RequestMapping("/users")
	//public List<User> getAllUsers(){
	//	return userService.getAllUsers();
	//}
	
	@RequestMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users= userService.getAllUsers();
		if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	/*@RequestMapping("/users/{id}")
	public User getUser(@PathVariable Long id){
		return userService.getUser(id);
	}*/
	
	@RequestMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id){
		 System.out.println("Fetching user with id " + id);
	        User user = userService.getUser(id);
	        if (user == null) {
	            System.out.println("user with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	/*@RequestMapping("/users/email/{email:.+}")
	public User getUserByEmail(@PathVariable String email){
		System.out.println(email);
		return userService.getByEmail(email);
	}*/
	
	@RequestMapping("/users/email/{email:.+}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email){
		 System.out.println("Fetching user with email " + email);
	        User user = userService.getByEmail(email);
	        if (user == null) {
	            System.out.println("user with email " + email + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	
	/*@RequestMapping(method = RequestMethod.POST, value = "/users/add")
	public void addUser(@RequestBody User user){
		userService.addUser(user);
	}
	*/
	
	@RequestMapping(method = RequestMethod.POST, value="/users/add")
	public ResponseEntity<Void> addUser(@RequestBody User user,   UriComponentsBuilder ucBuilder){
		 System.out.println("Creating user " + user.getName());
		 
	        if (userService.getByEmail(user.getEmail()) != null) {
	            System.out.println("user with email " + user.getEmail() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	        else{
	 
	        userService.addUser(user);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getUserId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	        }
	}
	
	/*@RequestMapping(method = RequestMethod.DELETE, value = "/users/delete/{id}")
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}*/
	
	
	  @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
	        System.out.println("Fetching & Deleting User with id " + id);
	 
	        User user = userService.getUser(id);
	        if (user == null) {
	            System.out.println("Unable to delete. User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        userService.deleteUser(id);
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	    }
	  
	/*@RequestMapping(method  = RequestMethod.PUT, value = "/users/update")
	public void userUpdate(@RequestBody User user){
		userService.updateUser(user);
	}*/
	  
	  @RequestMapping(value = "/users/update/{id}", method = RequestMethod.PUT)
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
	         
	        userService.updateUser(currentUser);
	        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	    }


}
