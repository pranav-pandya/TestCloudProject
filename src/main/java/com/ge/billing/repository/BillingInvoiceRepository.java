package com.ge.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.billing.model.Invoice;

@Repository
public interface BillingInvoiceRepository extends JpaRepository<Invoice, Integer> {
	//DO NOT Delete this File. It is using in saving logic.
	
}
