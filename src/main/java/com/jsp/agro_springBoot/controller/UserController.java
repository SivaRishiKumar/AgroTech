package com.jsp.agro_springBoot.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springBoot.dao.UserCrud;
import com.jsp.agro_springBoot.dto.Address;
import com.jsp.agro_springBoot.dto.EmailDetails;
import com.jsp.agro_springBoot.dto.User;
import com.jsp.agro_springBoot.service.UserService;
import com.jsp.agro_springBoot.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

	@Autowired
	private UserService ser;
	
	
	@PostMapping("/saveuser")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		
		return ser.saveUserdetails(user);
	}
	
	@GetMapping("/fetchuser")
	public ResponseEntity<ResponseStructure<User>> fetchByid(@RequestParam int id){
		return ser.fetchByid(id);
    }
	
	@PostMapping("/deleteuser")
	public ResponseEntity<ResponseStructure<User>> deleteByid(@RequestParam int id){
		return ser.deleteByid(id);
	}
	
	@PostMapping("/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return ser.updateByMovie(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<User>> login(@RequestParam String email,String pwd){
		return ser.loginUser(email, pwd);
	}
	
	@GetMapping("/otp")
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(@RequestParam String email){
		return ser.sendOtp(email);
	}
	
	
	
	@PostMapping("/attachment")
	public ResponseEntity<ResponseStructure<String>> attachment(@RequestBody EmailDetails details){
		return ser.attachImage(details);
	} 
	
	
	
	
	
}
