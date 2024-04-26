package com.jsp.agro_springBoot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro_springBoot.dto.Image;
import com.jsp.agro_springBoot.dto.User;

public interface Repo extends JpaRepository<User, Integer>{
               @Query("select a from User a where a.email=?1")
               User fetchbyEmail(String email);
               
               @Query("select a from User a")
               List<User> fetchbyAlls();

               @Query("select a from User a where a.image=?1")
       		public User fetchByImage(Image id);

			
}
