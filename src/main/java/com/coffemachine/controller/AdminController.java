package com.coffemachine.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.coffemachine.module.Admin;
import com.coffemachine.services.AdminService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/")
	public List<Admin> getAllAdmins(){
		return adminService.getAllAdmins();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/{id}")
	public Admin getAdmin(@PathVariable Long id){
		return adminService.getAdmin(id);
	}
	
/*	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public void addAdmin(@RequestBody Admin admin){
		adminService.addAdmin(admin);
	}*/
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<Void> addAdmin(@RequestBody Admin admin, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Admin " + admin.getUsername());

		if (adminService.findOneByUsername(admin.getUsername()) != null) {
			System.out.println("A Station with name " + admin.getUsername() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			String role = "ADMIN";
			List<String> roles = new ArrayList<>();
			roles.add(role);
			admin.setRoles(roles);
			adminService.addAdmin(admin);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/")
	public void updateAdmin(@RequestBody Admin admin){
		adminService.updateAdmin(admin);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteAdmin(@PathVariable Long id){
		Admin appUser = adminService.getAdmin(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		if (appUser == null) {
			System.out.println("You admin account is not available");
		} else if (appUser.getUsername().equalsIgnoreCase(loggedUsername)) {
			throw new RuntimeException("You cannot delete your account");
		} else {
			adminService.deleteAdmin(id);
		}
		
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
			HttpServletResponse response) throws IOException {
		System.out.println("I am here ");
		String token = null;
		Admin admin = adminService.findOneByUsername(username);
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		if (admin != null && admin.getPassword().equals(password)) {
			token = Jwts.builder().setSubject(username).claim("roles", admin.getRoles()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			tokenMap.put("token", token);
			tokenMap.put("user", admin);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
		} else {
			tokenMap.put("token", null);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
		}

	}
}
	