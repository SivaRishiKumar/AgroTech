package com.jsp.agro_springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro_springBoot.dto.Comment;
import com.jsp.agro_springBoot.service.CommentService;
import com.jsp.agro_springBoot.util.ResponseStructure;

@RestController
public class CommentController {
	@Autowired
	private CommentService service;
	
	@PostMapping("/saveComment")
	public ResponseEntity<ResponseStructure<Comment>> saveComment(@RequestParam int userid,@RequestParam int postid, @RequestParam String comment){
		return service.saveComment(userid, postid, comment);
		
	}
	@DeleteMapping("/Delete")
	public ResponseEntity<ResponseStructure<Comment>>deleteComment(@RequestParam int id){
		return service.deleteComment(id);
	}

}
