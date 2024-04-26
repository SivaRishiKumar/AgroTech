package com.jsp.agro_springBoot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro_springBoot.dto.Equipment;

public interface RepoEquipment extends JpaRepository<Equipment, Integer>{

	@Query("select e from Equipment e where e.name=?1")
	 public Equipment fetchByname(String name);

}
