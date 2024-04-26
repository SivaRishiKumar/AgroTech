package com.jsp.agro_springBoot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime starDateTime;
	private LocalDateTime endDateTime;
	@ManyToOne
	private Equipment equipment;
	@OneToOne
	private PaymentHistory pH;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getStarDateTime() {
		return starDateTime;
	}
	public void setStarDateTime(LocalDateTime starDateTime) {
		this.starDateTime = starDateTime;
	}
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public PaymentHistory getpH() {
		return pH;
	}
	public void setpH(PaymentHistory pH) {
		this.pH = pH;
	}
	public Rental(LocalDateTime starDateTime, LocalDateTime endDateTime, Equipment equipment, PaymentHistory pH) {
		super();
		this.starDateTime = starDateTime;
		this.endDateTime = endDateTime;
		this.equipment = equipment;
		this.pH = pH;
	}
	public Rental() {
		super();
	}
	
	
	
	
}
