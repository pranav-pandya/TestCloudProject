package com.ge.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ge.billing.model.CustomerAccount;

@Repository
public interface BillingCustomerAccountRepository extends JpaRepository<CustomerAccount, Integer> {
	
	@Transactional
	@Query(" select ca from CustomerAccount ca where ca.custAcctNum = ?1 ")
	List<CustomerAccount> getCustmomerAcctTypeByBillToNum(int custAcctNum);
	
}
