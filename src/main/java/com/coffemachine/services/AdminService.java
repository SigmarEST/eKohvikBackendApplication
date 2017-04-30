package com.coffemachine.services;

import java.util.List;

import com.coffemachine.module.Admin;

public interface AdminService {
	
	public List<Admin> getAllAdmins();
	
	public Admin getAdmin(Long id);
	
	public void addAdmin(Admin admin);
	
	public void deleteAdmin(Long id);
	
	public void updateAdmin(Admin admin);
	
	public Admin findOneByUsername(String username);
}
