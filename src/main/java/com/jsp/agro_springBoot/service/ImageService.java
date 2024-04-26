package com.jsp.agro_springBoot.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springBoot.dao.ImageCrud;
import com.jsp.agro_springBoot.dao.UserCrud;
import com.jsp.agro_springBoot.dto.Image;
import com.jsp.agro_springBoot.dto.User;
import com.jsp.agro_springBoot.util.ResponseStructure;

@Service
public class ImageService {

	@Autowired
	private ImageCrud dao;
	@Autowired
	private UserCrud crud;
	
	
	public ResponseEntity<ResponseStructure<Image>> uploadProfile(int id,String name, MultipartFile file) throws IOException{
		User userDb = crud.fetchByid(id);
		if(userDb!=null) {
            Image db = dao.uploadProfile(file, name);
			userDb.setImage(db);
			crud.updateUser(userDb);
			ResponseStructure<Image> r=new ResponseStructure<Image>();
			r.setMessage(" save img successfully......");
			r.setDetails(db);
			r.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.ACCEPTED);
		}else {
			throw null;
		}
		
	}
	
	
	
	public ResponseEntity<ResponseStructure<Image>> fetchById(int id){
		Image imageDb = dao.fetchById(id);
		if(imageDb!=null) {
			ResponseStructure<Image> r=new ResponseStructure<Image>();
			r.setMessage("fetch image Successfully.....");
			r.setDetails(imageDb);
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.FOUND);
		}else {
			throw null;
		}
	}
	
	public ResponseEntity<ResponseStructure<Image>> deleteById(int id){
		Image imageDb = dao.deleteById(id);
		if(imageDb!=null) {
			ResponseStructure<Image> r=new ResponseStructure<Image>();
			r.setMessage("delete image Successfully.....");
			r.setDetails(imageDb);
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.FOUND);
		}else {
			throw null;
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Image>> updateImage(int id,String name, MultipartFile file) throws IOException{
		Image imageDb=dao.updateImage(id, name,file);
		if(imageDb!=null) {
			 ResponseStructure<Image> r=new ResponseStructure<Image>();
			 r.setDetails(imageDb);
			 r.setMessage("update image successfully......!");
			 r.setStatus(HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.ACCEPTED);
		}else
			throw null;
		
	}



	

}
