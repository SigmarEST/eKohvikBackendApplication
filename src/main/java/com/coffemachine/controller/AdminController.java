package com.coffemachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coffemachine.module.Admin;
import com.coffemachine.services.AdminService;

@RestController
@RequestMapping("/coffemachine")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@RequestMapping("/admin")
	public List<Admin> getAllAdmins(){
		return adminService.getAllAdmins();
	}
	
	@RequestMapping("/admin/{id}")
	public Admin getAdmin(@PathVariable Long id){
		return adminService.getAdmin(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/admin/add")
	public void addAdmin(@RequestBody Admin admin){
		adminService.addAdmin(admin);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "admin/update")
	public void updateAdmin(@RequestBody Admin admin){
		adminService.updateAdmin(admin);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "admin/delete/{id}")
	public void deleteAdmin(@PathVariable Long id){
		adminService.deleteAdmin(id);
	}
}
	