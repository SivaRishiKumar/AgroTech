package com.jsp.agro_springBoot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro_springBoot.dto.Rental;
import com.jsp.agro_springBoot.repo.RepoRental;

@Repository
public class RentalCrud {
	
		@Autowired
		private RepoRental repo;
		
		public Rental saveRental(Rental rental) {
			return repo.save(rental);
			
		}
	

	
}
