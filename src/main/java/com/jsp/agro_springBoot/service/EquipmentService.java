package com.jsp.agro_springBoot.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro_springBoot.dao.EquipmentDto;
import com.jsp.agro_springBoot.dao.UserCrud;
import com.jsp.agro_springBoot.dto.Equipment;
import com.jsp.agro_springBoot.dto.User;
import com.jsp.agro_springBoot.util.ResponseStructure;

@Service
public class EquipmentService {
	
		@Autowired
		private EquipmentDto crud;
		@Autowired
		private UserCrud crud1;

		public ResponseEntity<ResponseStructure<Equipment>> save(int userid, Equipment equipment) throws IOException {
			User db = crud1.fetchByid(userid);
			if (db != null) {
				Equipment DB =  new Equipment();
				DB.setName(equipment.getName());
				DB.setQuantity(equipment.getQuantity());
				DB.setCostPerHour(equipment.getCostPerHour());
				DB.setUser(db);
				ResponseStructure<Equipment> eq = new ResponseStructure<Equipment>();
				eq.setDetails(crud.saveEquipment(DB));
				eq.setMessage("save successfully");
				eq.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Equipment>>(eq, HttpStatus.CREATED);
			} else {
				return null;

			}
		}

		public ResponseEntity<ResponseStructure<Equipment>> fetchById(int id) {
			Equipment db = crud.fetchById(id);
			if (db != null) {
				ResponseStructure<Equipment> r = new ResponseStructure<Equipment>();
				r.setDetails(db);
				r.setMessage("fetch successfully........");
				r.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<Equipment>>(r, HttpStatus.FOUND);
			} else {
				return null;

			}
		}

		public ResponseEntity<ResponseStructure<Equipment>> UpdateEquipment(Equipment equipment) {
			Equipment db = crud.fetchById(equipment.getId());
			if (db != null) {
				ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
				m.setDetails(crud.UpdateEquipment(equipment));
				m.setMessage(" update msg is successed...");
				m.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Equipment>>(m, HttpStatus.CREATED);
			} else {
				return null;
			}
		}

		

		public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(int id) {
			Equipment db = crud.DeleteById(id);
			if (db != null) {
				ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
				m.setDetails(db);
				m.setMessage(" delete msg is successed...");
				m.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<Equipment>>(m, HttpStatus.FOUND);
			} else {
				return null;
			}
		}
		public ResponseEntity<ResponseStructure<List<Equipment>>> fetchEquipmentAll() {
				List<Equipment> db = crud.fetchEquipmentAll();
		    if (db != null) {
		        ResponseStructure<List<Equipment>> responseStructure = new ResponseStructure<>();
		        responseStructure.setDetails(db);
		        responseStructure.setMessage("List of all equipments fetched successfully");
		        responseStructure.setStatus(HttpStatus.OK.value());

		        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		    } else {
		        return null;
		    }

		}

		//print on all names
//		public ResponseEntity<ResponseStructure<List<Equipment>>> fetchByName1(String name) throws IOException{
//			List<Equipment> db = crud.fetchName(name);
//			ResponseStructure<List<Equipment>> rsl = new ResponseStructure<List<Equipment>>();
//			if(db!=null) {
//				rsl.setData(db);
//				rsl.setMessage("Data fetch successfully.....");
//				rsl.setStatus(HttpStatus.FOUND.value());
//				return new ResponseEntity<ResponseStructure<List<Equipment>>>(rsl,HttpStatus.FOUND);
//			}else throw new UserNotFound(" Name does not exists.....");
//			
//		}
		public ResponseEntity<ResponseStructure<Equipment>> fetchByName(String name){
			Equipment db = crud.fetchByName(name);
			if(db!=null) {
				ResponseStructure<Equipment> m=new ResponseStructure<>();
				m.setDetails(db);
				m.setMessage("fetched Equipments Based on name");
				m.setStatus(HttpStatus.FOUND.value());
				
				return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
			}
			else {
				return null;
			}
		}
			
		
		
	}

