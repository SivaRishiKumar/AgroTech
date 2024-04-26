package com.jsp.agro_springBoot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro_springBoot.dto.Equipment;
import com.jsp.agro_springBoot.repo.RepoEquipment;

@Repository
public class EquipmentDto {
	
		@Autowired
		private RepoEquipment repo;

		public Equipment saveEquipment(Equipment equipment) {
			
			return repo.save(equipment);

		}
		
		public Equipment fetchById(int id) {
			Optional<Equipment> db = repo.findById(id);
			if(db.isPresent()) {
				return db.get();
			}
			return null;
			
		}
		
		public Equipment UpdateEquipment(Equipment equipment) {
			 Optional<Equipment> db = repo.findById(equipment.getId());

			 if(db.isPresent()) {
				 Equipment equi=db.get();

				 if(equipment.getName()==null) {
					 equipment.setName(equi.getName());
				 }
					
					if(equipment.getCostPerHour()==0) {
						equipment.setCostPerHour(equi.getCostPerHour());
					}
					if(equipment.getQuantity()==0) {
						equipment.setQuantity(equi.getQuantity());
					}
					if(equipment.getUser()==null) {
						equipment.setUser(equi.getUser());
					}
				 return repo.save(equipment);
			 }
			return null;
			
		}
		
		
		
		public Equipment DeleteById(int id) {
			Optional<Equipment> db = repo.findById(id);
			if (db.isEmpty()) {
				return null;
			} else {
				repo.deleteById(id);
				return db.get();
			}
		}

		
		public List<Equipment>fetchEquipmentAll(){
			return repo.findAll();
		}
//		public Optional<Equipment> findById(int id) {
//			// TODO Auto-generated method stub
//			return null;
//		}

//		public List<Equipment> fetchName(String name) {//print on all names
//			List<Equipment> db = repo.findAll();
//			return db;
//		}
		

		public Equipment fetchByName(String name) {
			Equipment db = repo.fetchByname(name);
			if(db!=null) {
				return db;
			}
			else {
				return null;
			}
		}


		
		
	}

