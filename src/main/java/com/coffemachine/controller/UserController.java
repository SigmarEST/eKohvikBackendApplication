package com.coffemachine.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coffemachine.mail.MailSender;
import com.coffemachine.model.User;
import com.coffemachine.services.PurchaseService;
import com.coffemachine.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	@Qualifier("MyMailSender")
	MailSender mailSender;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUser(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/email/{email:.+}")
	public User getUserByEmail(@PathVariable String email) {
		System.out.println(email);
		return userService.getByEmail(email);
	}

	// for station only
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/station/email/{email:.+}")
	public User getUserByEmailForStation(@PathVariable String email) {
		System.out.println(email);
		return userService.getByEmail(email);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public void addUser(@RequestBody User user) {

		if (userService.getByEmail(user.getEmail()) == null) {
			user.setCreatedDate(new Date());
			userService.addUser(user);

			try {

				String from = "khasanboyakbarov@gmail.com";
				String to = user.getEmail();
				String subject = "Greeting";
				String body = "New user account is created with this email address in eKohvik!";
				mailSender.sendMail(from, to, subject, body);

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}

	// for station only
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.POST, value = "/add/")
	public User addUserFromAPI(@RequestBody User user) {
		if (userService.getByEmail(user.getEmail()) == null) {
			user.setCreatedDate(new Date());
			userService.addUser(user);
			User createdUser = userService.getByEmail(user.getEmail());
			try {

				String from = "khasanboyakbarov@gmail.com";
				String to = user.getEmail();
				String subject = "Greeting";
				String body = "New user account is created with this email address in eKohvik!";
				mailSender.sendMail(from, to, subject, body);

			} catch (Exception ex) {
				System.out.println(ex);
			}
			return createdUser;
		} else {
			return null;
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteUser(@PathVariable Long id) {
		for (int i = 0; i < purchaseService.getAllPurchases().size(); i++) {
			if (purchaseService.getAllPurchases().get(i).getUser().getUserId() == id) {
				purchaseService.deletePurchase(purchaseService.getAllPurchases().get(i));
			}
		}
		
		try {
			User user = userService.getUser(id);

			String from = "khasanboyakbarov@gmail.com";
			String to = user.getEmail();
			String subject = "Greeting";
			String body = "Your user account is deleted in eKohvik!";
			mailSender.sendMail(from, to, subject, body);

		} catch (Exception ex) {
			System.out.println(ex);
		}
		userService.deleteUser(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/")
	public void userUpdate(@RequestBody User user) {
		userService.updateUser(user);
	}

	/*
	 * @RequestMapping(value = "/users/update/{id}", method = RequestMethod.PUT)
	 * public ResponseEntity<User> updateUser(@PathVariable("id") Long
	 * id, @RequestBody User user) { System.out.println("Updating user " + id);
	 * 
	 * User currentUser = userService.getUser(id);
	 * 
	 * if (currentUser==null) { System.out.println("User with id " + id +
	 * " not found"); return new ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 * 
	 * currentUser.setName(user.getName());
	 * currentUser.setEmail(user.getEmail());
	 * currentUser.setCards(user.getCards());
	 * currentUser.setBalance(user.getBalance());
	 * 
	 * userService.updateUser(currentUser); return new
	 * ResponseEntity<User>(currentUser, HttpStatus.OK); }
	 */

}
