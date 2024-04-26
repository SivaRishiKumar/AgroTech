package com.jsp.agro_springBoot.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro_springBoot.dto.Image;

public interface Repo2 extends JpaRepository<Image, Integer>{

	Optional<Image> findByName(String name);


}
