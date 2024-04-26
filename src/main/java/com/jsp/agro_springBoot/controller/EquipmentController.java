package com.jsp.agro_springBoot.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro_springBoot.dto.Equipment;
import com.jsp.agro_springBoot.service.EquipmentService;
import com.jsp.agro_springBoot.util.ResponseStructure;

@RestController
public class EquipmentController {
	
		@Autowired
		private EquipmentService service;
		
		@PostMapping("/saveEquipment")
		public ResponseEntity<ResponseStructure<Equipment>>saveEquipment(@RequestParam int userid,@RequestBody Equipment equipment) throws IOException{
			return service.save(userid, equipment);
			
		}
		@GetMapping("/fetchequipment")
		public ResponseEntity<ResponseStructure<Equipment>>fetchById(@RequestParam int id){
			return service.fetchById(id);
		}
		@PutMapping("/updateEquipment")
		public ResponseEntity<ResponseStructure<Equipment>>updateEquipment(@RequestBody Equipment equipment){
			return service.UpdateEquipment(equipment);
			
		}
		@DeleteMapping("/deleteEquipment")
		public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(@RequestParam int id){
			return service.deleteEquipment(id);
		}
		@GetMapping("/fetchAllEquipment")//fetchAll
		public ResponseEntity<ResponseStructure<List<Equipment>>> fetchPostAll() {
			return service.fetchEquipmentAll();
		}
//		@GetMapping("/fetchByName")//print on all names
//		public ResponseEntity<ResponseStructure<List<Equipment>>> fetchName(@RequestBody String name) throws IOException{
//			//return service.fetchByName(name);
//		}
		@GetMapping("/fetchByNameEquipment")
		public ResponseEntity<ResponseStructure<Equipment>> fetchByName(@RequestParam String name){
			return service.fetchByName(name);
		}

	}

