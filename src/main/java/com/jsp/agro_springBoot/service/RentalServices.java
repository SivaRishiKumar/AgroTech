package com.jsp.agro_springBoot.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro_springBoot.dao.EquipmentDto;
import com.jsp.agro_springBoot.dao.PaymentHistoryCrud;
import com.jsp.agro_springBoot.dao.RentalCrud;
import com.jsp.agro_springBoot.dao.UserCrud;
import com.jsp.agro_springBoot.dto.Equipment;
import com.jsp.agro_springBoot.dto.PaymentHistory;
import com.jsp.agro_springBoot.dto.Rental;
import com.jsp.agro_springBoot.dto.User;
import com.jsp.agro_springBoot.util.ResponseStructure;

@Service
public class RentalServices {
	@Autowired
	private RentalCrud rdao;
	
	@Autowired
	private PaymentHistoryCrud phdao;
	
	@Autowired
	private EquipmentDto edao;
	
	@Autowired
	private UserCrud udao;
	
	public ResponseEntity<ResponseStructure<Rental>> saveRental(int eid,int uid,String startdate,String enddate){
		Equipment edb = edao.fetchById(eid);
		if(edb!=null) {
			User udb = udao.fetchByid(uid);
			if(udb!=null) {
				Rental rental = new Rental();
				rental.setEquipment(edb);
				rental.setStarDateTime(LocalDateTime.parse(startdate));
				rental.setEndDateTime(LocalDateTime.parse(enddate));
				
				PaymentHistory paymenthistory = new PaymentHistory();
				   double amount = calculateAmount(rental);
				   paymenthistory.setAmount(amount);
				   phdao.savePayment(paymenthistory);
				   rdao.saveRental(rental);
				   ResponseStructure<Rental> rs = new ResponseStructure<Rental>();
				   rs.setDetails(rental);
				   rs.setMessage("rental added");
				   rs.setStatus(HttpStatus.OK.value());
				   return new ResponseEntity<ResponseStructure<Rental>>(rs,HttpStatus.OK);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	private double calculateAmount(Rental rental) {
		long minutes = Duration.between(rental.getStarDateTime(), rental.getEndDateTime()).toMinutes();
		double hours = (double) minutes /60;
		double amount = hours * rental.getEquipment().getCostPerHour();
		return amount;
	}
}
