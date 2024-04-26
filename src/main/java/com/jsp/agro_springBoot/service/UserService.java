package com.jsp.agro_springBoot.service;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springBoot.dao.AddressCrud;
import com.jsp.agro_springBoot.dao.UserCrud;
import com.jsp.agro_springBoot.dto.EmailDetails;
import com.jsp.agro_springBoot.dto.Image;
import com.jsp.agro_springBoot.dto.User;
import com.jsp.agro_springBoot.repo.Repo;
import com.jsp.agro_springBoot.repo.Repo2;
import com.jsp.agro_springBoot.util.ResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service

public class UserService {
	
	@Autowired
	private UserCrud crud;
	
	@Autowired
	private JavaMailSender mail;
	
	@Autowired
	private Repo2 repo2;
	
	
	public ResponseEntity<ResponseStructure<User>> saveUserdetails(User user){
	
		ResponseStructure<User> m = new ResponseStructure<>();
		m.setDetails(crud.saveUser(user));
		m.setMessage("successfully register user details...");
		m.setStatus(HttpStatus.CREATED.value());
		SimpleMailMessage sm = new SimpleMailMessage();
		
		sm.setFrom("sakesivakumar7799@gmail.com");
		sm.setTo(user.getEmail());
		sm.setText("WELCOME TO AGRO Tech--->G10 "+user.getFirstName());
		sm.setSubject(user.getEmail()+" registration is successfully completed......");
		mail.send(sm);
		return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.CREATED);
		
	}
	
	
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(String email){
		User db = crud.fetchByEmail(email);
		if(db!=null) {
		Random random = new Random();
		int value = random.nextInt();
		ResponseStructure<Integer> m = new ResponseStructure<>();
		m.setDetails(value);
		m.setMessage("otp sent successfully");
		m.setStatus(HttpStatus.FOUND.value());
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("sakesivakumar7799@gmail.com");
		msg.setTo(email);
		msg.setSubject("otp verification");
		msg.setText("please enter otp: " + value);
		mail.send(msg);
		return new ResponseEntity<ResponseStructure<Integer>>(m,HttpStatus.FOUND);
		
		}else {
			return null;
		}
		
	}
	
	
	public ResponseEntity<ResponseStructure<User>> fetchByid(int id){
		User db = crud.fetchByid(id);
		if(db!=null) {
		ResponseStructure<User> m = new ResponseStructure<>();
		m.setDetails(crud.fetchByid(id));
		m.setMessage("successfully fetch the details");
		m.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.CREATED);
		}else {
			return null;
		}
	}
	
	public ResponseEntity<ResponseStructure<User>> deleteByid(int id){
		User db = crud.deleteByid(id);
		if(db!=null) {
			ResponseStructure<User> m = new ResponseStructure<>();
			m.setDetails(db);
			m.setMessage("successfully deleted movie");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.CREATED);
		}else {
			return null;
		}
	}
	
	public ResponseEntity<ResponseStructure<User>> updateByMovie(User m){
		User db = crud.fetchByid(m.getId());
		if(db!=null) {
			ResponseStructure<User> m1 = new ResponseStructure<User>();
			m1.setDetails(crud.updateUser(db));
			m1.setMessage("successfully");
			m1.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m1,HttpStatus.FOUND);
		}else {
			return null;
		}
	}
	
	public ResponseEntity<ResponseStructure<User>> loginUser(String email,String pwd){
		User db = crud.fetchByEmail(email);
		if(db.getPwd().equals(pwd)) {
			ResponseStructure<User> m1 = new ResponseStructure<User>();
			m1.setDetails(crud.fetchByEmail(email));
			m1.setMessage("successfully login");
			m1.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m1,HttpStatus.FOUND);
		}else {
			return null;
		}
	}
	

	public ResponseEntity<ResponseStructure<String>> attachImage(EmailDetails details) {
		User db = crud.fetchByEmail(details.getRecipient());
		MimeMessage mm = mail.createMimeMessage();
		MimeMessageHelper mmh;
		try {
			mmh = new MimeMessageHelper(mm,true);
			mmh.setFrom("sakesivakumar7799@gmail.com");
			mmh.setTo(db.getEmail());
			mmh.setSubject(db.getFirstName()+" send ur pic bro");
			
			FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
			
			mmh.addAttachment(file.getFilename(), file);
			mail.send(mm);
			ResponseStructure<String> m1 = new ResponseStructure<>();
			m1.setDetails("success");
			m1.setMessage("successfully login");
			m1.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(m1,HttpStatus.FOUND);
			
		}catch(MessagingException me){
			 return null;
		}
		
	}
	
	
	
	
	
	
	

}
