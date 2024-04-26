package com.jsp.agro_springBoot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro_springBoot.dto.Post;

public interface RepoPost extends JpaRepository<Post, Integer>{

}
