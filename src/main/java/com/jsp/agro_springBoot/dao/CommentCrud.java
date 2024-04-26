package com.jsp.agro_springBoot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro_springBoot.dto.Comment;
import com.jsp.agro_springBoot.dto.User;
import com.jsp.agro_springBoot.repo.CommentRepo;

@Repository
public class CommentCrud {
	@Autowired
	private CommentRepo repo;
	

	public Comment saveComment(Comment comment) {
		return repo.save(comment);
		
	}

	public Comment deleteComment(int id) {
		Optional<Comment> db = repo.findById(id);
		if (db.isPresent()) {
			repo.delete(db.get());
			
			return db.get();
		}
		else {
			return null;
		}
		
	}
	
	public Comment fetchCommentById(int id) {
		Optional<Comment> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		return null;
	}
}
