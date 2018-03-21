package com.ge.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ge.billing.dto.ContractDTO;
import com.ge.billing.service.BillingContractService;

@CrossOrigin
@RestController
@Component
@RequestMapping(value="/")
public class BillingContractController {

	@Autowired
    private BillingContractService billingContractService;
	
	@RequestMapping(value ="/generateAllInvoices",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<ContractDTO> getContractList() throws Exception{
		return billingContractService.getContractList(null);
	}
	
	@RequestMapping(value ="/generateAllInvoices/{agreementNum}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<ContractDTO> getContractListByAgreementId(@PathVariable("agreementNum") String strAgreementNum) throws Exception{
		return billingContractService.getContractList(strAgreementNum);
	}
}