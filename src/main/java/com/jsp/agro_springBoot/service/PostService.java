package com.jsp.agro_springBoot.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro_springBoot.dao.ImageCrud;
import com.jsp.agro_springBoot.dao.PostCrud;
import com.jsp.agro_springBoot.dao.UserCrud;
import com.jsp.agro_springBoot.dto.Image;
import com.jsp.agro_springBoot.dto.Post;
import com.jsp.agro_springBoot.dto.User;
import com.jsp.agro_springBoot.util.ResponseStructure;

@Service
public class PostService {
	@Autowired
	private ImageCrud dao;
	@Autowired
	private UserCrud crud;
	@Autowired
	private PostCrud crud1;

	public ResponseEntity<ResponseStructure<Post>> savePost(int id, MultipartFile file, String caption,String name,String location)
			throws IOException {
		User userdb = crud.fetchByid(id);
		if (userdb != null) {
			Image i = new Image();
			i.setName(file.getOriginalFilename());
			i.setData(file.getBytes());

			// Save the Image entity first
			Image savedImage = dao.saveImages(file, name);

			// Create the Post entity and associate it with the saved Image
			Post post1 = new Post();
			post1.setImg(savedImage);
			post1.setDate(LocalDate.now());
			post1.setTime(LocalTime.now());
			post1.setCaption(caption);

			// Save the Post entity
			Post postDb = crud1.savePost(savedImage, location, caption);

			// Update the User entity with the new Post
			List<Post> postList = new ArrayList<>();
			postList.add(postDb);
			postList.addAll(userdb.getPost());
			userdb.setPost(postList);

			// Save the updated User entity
			User updatedUserDbObject = crud.updateUser(userdb);

			ResponseStructure<Post> postResponseStructor = new ResponseStructure<>();
			postResponseStructor.setDetails(postDb);
			postResponseStructor.setMessage("User Given post is saved Successfully");
			postResponseStructor.setStatus(HttpStatus.OK.value());

			return new ResponseEntity<>(postResponseStructor, HttpStatus.OK);
		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<Post>> fetchPostById(int id) {
		Post postDbObject = crud1.fetchPostById(id);

		if (postDbObject != null) {
			ResponseStructure<Post> postResponseStructor = new ResponseStructure<Post>();

			postResponseStructor.setDetails(postDbObject);
			postResponseStructor.setMessage("This is the Post You have in the database with the given id:  " + id);
			postResponseStructor.setStatus(HttpStatus.OK.value());

			return new ResponseEntity<>(postResponseStructor, HttpStatus.OK);
		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<Post>> deletePostById(int id) {
		Post postDbObject = crud1.fetchPostById(id);

		if (postDbObject != null) {

			List<User> users = crud.fetchAllUser();
			for (User userObj : users) {
				List<Post> userPosts = userObj.getPost();
				if (userPosts.contains(postDbObject)) {
					userPosts.remove(postDbObject);
					crud.updateUser(userObj);

					crud1.deletePostById(id);
				}
			}
			ResponseStructure<Post> postResponseStructor = new ResponseStructure<Post>();

			postResponseStructor.setDetails(postDbObject);
			postResponseStructor.setMessage("Post is Deleted Success fully");
			postResponseStructor.setStatus(HttpStatus.OK.value());

			return new ResponseEntity<>(postResponseStructor, HttpStatus.FOUND);
		} else {
			return null;
		}
	}

//	public ResponseEntity<ResponseStructure<List<Post>>> fetchAll() {
//		List<Post> db = dao.fetchAll();
//		if (db.size() != 0) {
//			ResponseStructure<List<Post>> structure1 = new ResponseStructure<List<Post>>();
//			structure1.setData(db);
//			structure1.setMessage("user fetched successfully");
//			structure1.setStatus(HttpStatus.FOUND.value());
//			return new ResponseEntity<ResponseStructure<List<Post>>>(structure1, HttpStatus.FOUND);
//		}
//		throw new UserNotFound("search:all data");
//	}
	
	public ResponseEntity<ResponseStructure<List<Post>>> fetchPostAll() {
	    List<Post> db = crud1.fetchPostAll();

	    
	        ResponseStructure<List<Post>> responseStructure = new ResponseStructure<>();
	        responseStructure.setDetails(db);
	        responseStructure.setMessage("List of all posts fetched successfully");
	        responseStructure.setStatus(HttpStatus.OK.value());

	        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	   
	}
	}



