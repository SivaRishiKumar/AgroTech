package com.jsp.agro_springBoot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro_springBoot.dto.Address;
import com.jsp.agro_springBoot.repo.Repo1;

@Repository
public class AddressCrud {

	@Autowired
	private Repo1 repo;
	
	public Address saveAddress(Address ad) {
		return repo.save(ad);
	}
	
	

	public Address deleteByid(int id) {
		Optional<Address> db = repo.findById(id);
		if(db.isPresent()) {
			repo.delete(db.get());
			repo.deleteById(id);
			return db.get();
			
		}else {
			return null;
		}
	}

	public Address updateUser(Address ad) {
		Optional<Address> db = repo.findById(ad.getId());
		if(db.isPresent()) {
			Address ad1 = db.get();
			if(ad1.getHouseNumber()==null) {
				ad.setHouseNumber(ad1.getHouseNumber());
			}else if(ad1.getStreet()==null){
				ad.setDistrict(ad1.getDistrict());
			}
			return repo.save(ad);
		}else {
			return null;
		}
	}
	
	
	
}
