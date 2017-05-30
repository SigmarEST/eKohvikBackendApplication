package com.coffemachine.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.coffemachine.AbstractTest;
import com.coffemachine.model.Admin;
import com.coffemachine.services.AdminService;

@Transactional
public class AdminServiceTest extends AbstractTest {
	
	@Autowired
	private AdminService adminService;
	
	@Test
	public void testGetAllAdmins(){
		List<Admin> list = adminService.getAllAdmins();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		
	}
	
	@Test
	public void testGetAdmin(){
		Admin admin = adminService.getAdmin(2L);
		Assert.assertNotNull(admin);
		Assert.assertEquals(2, admin.getId().intValue());
	}
	
	@Test
	public void testFindOneByUsername(){
		Admin admin = adminService.findOneByUsername("admin2");
		Assert.assertNotNull(admin);
		Assert.assertEquals(2, admin.getId().intValue());
	}
	
	@Test
	public void testAddAdmin(){
		Admin admin = new Admin();
		admin.setEmail("myemail@gmail.com");
		admin.setName("Ahmed");
		admin.setPassword("password");
		admin.setUsername("username");
		adminService.addAdmin(admin);
		
		Assert.assertNotNull(adminService.findOneByUsername("username"));
		Assert.assertEquals("password", adminService.findOneByUsername("username").getPassword());
	}
	
	@Test
	public void testDeleteAdmin(){
		Admin admin = new Admin();
		admin.setEmail("myemail@gmail.com");
		admin.setName("Ahmed");
		admin.setPassword("password");
		admin.setUsername("usernamem");
		adminService.addAdmin(admin);
		
		//Assert.assertNotNull(adminService.findOneByUsername("username"));
		adminService.deleteAdmin(adminService.findOneByUsername("usernamem").getId());
		Assert.assertNull(adminService.findOneByUsername("usernamem"));
		
		
	}

}
