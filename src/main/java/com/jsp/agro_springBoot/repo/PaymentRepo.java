package com.jsp.agro_springBoot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro_springBoot.dto.PaymentHistory;

public interface PaymentRepo extends JpaRepository<PaymentHistory, Integer>{

}
