package com.jsp.agro_springBoot.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro_springBoot.dto.Image;
import com.jsp.agro_springBoot.dto.Post;
import com.jsp.agro_springBoot.repo.RepoPost;

@Repository
public class PostCrud {
	
		@Autowired
		private RepoPost repos;
		@Autowired
		private Post post;

//		public Post savePost(Post post) {
	//
//			return repo.save(post);
//		}
		public Post savePost(Image image,String location,String Caption) {
			post.setCaption(Caption);
			post.setDate(LocalDate.now());
			post.setTime(LocalTime.now());
			post.setLocation(location);
			post.setImg(image);
			return repos.save(post);
			
		}

		

		public Post fetchPostById(int id) {
			Optional<Post> postObject = repos.findById(id);

			if(postObject.isPresent()) {
				return postObject.get();
			}else {
				return null;
			}
		}

		public void deletePostById(int id) {
			Optional<Post> postDb = repos.findById(id);
			if(postDb.isPresent()) {
				
				repos.deleteById(id);
			}
		}



		
		
		public Post updatePost(Post p) {
			Optional<Post> db = repos.findById(p.getId());
			Post post=db.get();
			if(db.isPresent()) {
				return repos.save(post);
			}
			return null;
			
		}
		public List<Post>fetchPostAll(){
			return repos.findAll();
		}
	
}
