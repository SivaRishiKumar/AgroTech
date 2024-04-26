package com.jsp.agro_springBoot.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springBoot.dto.Post;
import com.jsp.agro_springBoot.service.PostService;
import com.jsp.agro_springBoot.util.ResponseStructure;

@RestController
public class PostControll {
	@Autowired
	private PostService service;
	
	
	@PostMapping("/savePost")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestParam int id, @RequestParam MultipartFile file,
			 @RequestParam String caption,@RequestParam String location,@RequestParam String name)throws IOException {
		return service.savePost(id, file, caption,location,name);

	}
	
	@GetMapping("/fetchPostById")
	public ResponseEntity<ResponseStructure<Post>> fetchPostById(@RequestParam int id) {
		
		return service.fetchPostById(id);
	}
	
	@DeleteMapping("/deletePostById")
	public ResponseEntity<ResponseStructure<Post>> deletePostById(@RequestParam int id) {
		
		return service.deletePostById(id);
	}
	@GetMapping("/fetchPostAll")//fetchAll
	public ResponseEntity<ResponseStructure<List<Post>>> fetchPostAll() {
		return service.fetchPostAll();
	}

}
