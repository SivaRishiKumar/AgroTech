package com.jsp.agro_springBoot.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springBoot.dto.Address;
import com.jsp.agro_springBoot.dto.Image;
import com.jsp.agro_springBoot.dto.User;
import com.jsp.agro_springBoot.repo.Repo;
import com.jsp.agro_springBoot.repo.Repo1;


@Repository
public class UserCrud {

	
	@Autowired
	private Repo repo;
	
	@Autowired
	private Repo1 repo1;
	
	@Autowired
	private AddressCrud acrud;
	
	
	
	public User saveUser(User user) {
		Address ad = new Address();
		ad = repo1.save(user.getAddress());
		
		User user1 = new User();
		
		user1.setAddress(ad);
		
		return repo.save(user);
	}
	
//	public String saveImage(byte[] bs,int id) throws IOException {
//		Optional<User> db = repo.findById(id);
//		User user = new User();
//		if(db.isPresent()) {
//			user.setPhoto(bs);
//			return "successfull";
//		}else {
//			return null;
//		}
//		
//	}
	
	public User fetchByid(int id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}else {
			return null;
		}
	}
	
	public User fetchByEmail(String email) {
		User db = repo.fetchbyEmail(email);
		if(db!=null) {
			return db;
		}else {
			return null;
		}
	}
	
	public User deleteByid(int id) {
		Optional<User> db = repo.findById(id);
			
			if(db.isPresent()) {
				repo.delete(db.get());
				Address ad = acrud.deleteByid(id);
				repo.deleteById(id);
				return db.get();
			}else {
				return null;
			}
	}
	
	public User updateUser(User user) {
		Optional<User> db = repo.findById(user.getId());
		if(db.isPresent()) {
			User u=db.get();
			if(u.getFirstName()==null) {
				user.setFirstName(u.getFirstName());
			}else if(u.getLastName()==null) {
				user.setLastName(u.getLastName());
			}else if(u.getAge()==0) {
				user.setAge(u.getAge());
			}else if(u.getPhone()==0) {
				user.setPhone(u.getPhone());
			}else if(u.getPwd()==null) {
				user.setPwd(u.getPwd());
			}else if(u.getEmail()==null) {
				user.setEmail(u.getEmail());
			}else if(u.getAddress()==null) {
				user.setAddress(u.getAddress());
			}else {
				if(user.getAddress().getId()==0) {
					user.getAddress().setId(u.getAddress().getId());
				}
				acrud.updateUser(user.getAddress());
			}
			return repo.save(user);
		}else {
			return null;
		}
	}

	

	public List<User> fetchAllUser() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	
	
	
}
