package com.jsp.agro_springBoot.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springBoot.dto.Image;
import com.jsp.agro_springBoot.service.ImageService;
import com.jsp.agro_springBoot.util.ResponseStructure;

@RestController
public class ImageController {

	@Autowired
	private ImageService service;
	
	
	
	@PostMapping("/uploadProfile")
	public ResponseEntity<ResponseStructure<Image>> uploadProfile(@RequestParam int id, String name,@RequestParam MultipartFile file) throws IOException{
		return  service.uploadProfile(id, name, file);
	}
	@GetMapping("/fetchImage")
	public ResponseEntity<ResponseStructure<Image>> fetchById(@RequestParam int id) {
		return service.fetchById(id);
	}
	@DeleteMapping("/deleteImage")
	public ResponseEntity<ResponseStructure<Image>> deleteById(@RequestParam int id){
		System.out.println("hello.....................");
		return service.deleteById(id);
	}
	@PutMapping("/updateImage")
	public ResponseEntity<ResponseStructure<Image>> updateImage(@RequestParam int id,@RequestParam String name,@RequestParam MultipartFile file) throws IOException{
		System.out.println("controller");
		return service.updateImage(id,name,file);
	}

}
