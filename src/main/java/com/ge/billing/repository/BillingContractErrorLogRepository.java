package com.ge.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ge.billing.model.ContractErrorLog;

@Repository
public interface BillingContractErrorLogRepository extends JpaRepository<ContractErrorLog, Integer> {
	
	
}
