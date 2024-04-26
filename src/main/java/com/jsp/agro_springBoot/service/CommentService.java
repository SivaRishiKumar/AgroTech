package com.jsp.agro_springBoot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro_springBoot.dao.CommentCrud;
import com.jsp.agro_springBoot.dao.PostCrud;
import com.jsp.agro_springBoot.dao.UserCrud;
import com.jsp.agro_springBoot.dto.Comment;
import com.jsp.agro_springBoot.dto.Post;
import com.jsp.agro_springBoot.dto.User;
import com.jsp.agro_springBoot.util.ResponseStructure;

@Service
public class CommentService {


		@Autowired
		private PostCrud crud;
		@Autowired
		private UserCrud crud1;
		@Autowired
		private CommentCrud crud2;
		
		public ResponseEntity<ResponseStructure<Comment>> saveComment(int userid,int postid,String  comment){
			Post postDb = crud.fetchPostById(postid);
			if(postDb!=null) {
				User userDb = crud1.fetchByid(userid);
				if(userDb!=null) {
				Comment c = new Comment();
				c.setComment(comment);
				c.setUser(userDb);
				crud2.saveComment(c);
				List<Comment> list=new ArrayList<Comment>();
				list.add(c);
				list.addAll(postDb.getComment());
				postDb.setComment(list);
				System.out.println("service");
				crud.updatePost(postDb);
				System.out.println("after service");
				ResponseStructure<Comment> r=new ResponseStructure<Comment>();
				r.setDetails(c);
				r.setMessage("comment saved successfully....!");
				r.setStatus(HttpStatus.CONTINUE.value());
				return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.CONTINUE);
				}else
					return null;
				
			}else
				return null;
		}
		
		
//		public ResponseEntity<ResponseStructure<Comment>> deleteComment(int id){
//			Comment commentDb = crud2.fetchCommentById(id);
//			if(commentDb!=null) {
//				List<Post> post=crud.fetchPostAll();
//				for(Post p:post) {
//					System.out.println("inside for each");
//					List<Comment> comment=p.getComment();
//					if(comment.contains(commentDb)) {
//						comment.remove(post);
//						crud.updatePost(p);
//						System.out.println("before delete");
//						crud2.deleteComment(id);
//						System.out.println("after delete");
//					}
//					}
//				ResponseStructure<Comment> r=new ResponseStructure<Comment>();
//				r.setData(commentDb);
//				r.setMessage("comment deleted successfully....!");
//				r.setStatus(HttpStatus.CONTINUE.value());
//				return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.CONTINUE);
//			}
//			else {
//				throw new  CommentNotFound("comment not found for id : "+id);
//			}
//				
//		}
		public ResponseEntity<ResponseStructure<Comment>> deleteComment(int id) {
		    Comment commentDb = crud2.fetchCommentById(id);
		  
		        List<Post> posts = crud.fetchPostAll();
		        for (Post p : posts) {
		            List<Comment> comments = p.getComment();
		            if (comments.contains(commentDb)) {
		                comments.remove(commentDb); // Remove the comment, not the post
		                crud.updatePost(p);
		                System.out.println("before delete");
		                crud2.deleteComment(id);
		                System.out.println("after delete");
		            }
		        }
		        ResponseStructure<Comment> r = new ResponseStructure<>();
		        r.setDetails(commentDb);
		        r.setMessage("comment deleted successfully....!");
		        r.setStatus(HttpStatus.CONTINUE.value());
		        return new ResponseEntity<>(r, HttpStatus.CONTINUE);
		   
		}

	
	
}
