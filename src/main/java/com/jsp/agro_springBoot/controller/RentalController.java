package com.jsp.agro_springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro_springBoot.dto.Rental;
import com.jsp.agro_springBoot.service.RentalServices;
import com.jsp.agro_springBoot.util.ResponseStructure;

@RestController
public class RentalController {
	@Autowired
	private RentalServices service;
	
	@PostMapping("/saverental")
	public ResponseEntity<ResponseStructure<Rental>> saveRental(@RequestParam int eid, @RequestParam int uid,@RequestParam String startdate,@RequestParam String enddate){
		return service.saveRental(eid,uid,startdate,enddate);
		
	}

}
