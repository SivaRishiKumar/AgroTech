package com.jsp.agro_springBoot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro_springBoot.dto.PaymentHistory;
import com.jsp.agro_springBoot.repo.PaymentRepo;

@Repository
public class PaymentHistoryCrud {
	@Autowired
	private PaymentRepo repo;
	public PaymentHistory savePayment(PaymentHistory paymenthistory) {
		return repo.save(paymenthistory);
	}

}
