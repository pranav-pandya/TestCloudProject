package com.ge.billing.service;

import java.util.ArrayList;
import java.util.List;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.billing.dto.CustomerAccountDTO;
import com.ge.billing.model.CustomerAccount;
import com.ge.billing.repository.BillingCustomerAccountRepository;

@Service
public class BillingCustomerAccountService {
	
	@Autowired
	BillingCustomerAccountRepository billingCustomerAccountRepository;
	private MapperFactory customerAccountMapperFactory = new DefaultMapperFactory.Builder().build();
	private BoundMapperFacade<CustomerAccount, CustomerAccountDTO> customerAccountBoundMapper = customerAccountMapperFactory.getMapperFacade(CustomerAccount.class, CustomerAccountDTO.class);

	public List<CustomerAccount> getCustmomerAcctTypeByBillToNum(int custAcctNum){
		List<CustomerAccount> CustomerAccountList = new ArrayList<CustomerAccount>();
		try {
			CustomerAccountList = billingCustomerAccountRepository.getCustmomerAcctTypeByBillToNum(custAcctNum);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return CustomerAccountList;
	}
	
}
