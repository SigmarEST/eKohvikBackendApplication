package com.coffemachine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffemachine.model.Admin;
import com.coffemachine.repository.AdminRepository;
import com.coffemachine.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired 
	AdminRepository adminRepository;
	//@Autowired
    //private RoleRepository roleRepository;
    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<Admin> getAllAdmins(){
		return (List<Admin>) adminRepository.findAll();
	}
	
	public Admin getAdmin(Long id){
		return adminRepository.findOne(id);
	}
	
	public void addAdmin(Admin admin){
			//admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
	       // admin.setRoles(new List(userRole));
	        adminRepository.save(admin);
	}
	
	public void deleteAdmin(Long id){
		adminRepository.delete(id);
	}
	
	public void updateAdmin(Admin admin){
		//admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
       // Role userRole = roleRepository.findByRole("ADMIN");
       // admin.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        adminRepository.save(admin);
	}

	@Override
	public Admin findOneByUsername(String username) {
		return adminRepository.findOneByUsername(username);
	}

}
