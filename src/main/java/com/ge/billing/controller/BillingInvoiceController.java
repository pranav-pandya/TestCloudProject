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

import com.ge.billing.dto.InvoiceDTO;
import com.ge.billing.service.BillingContractService;
import com.ge.billing.service.BillingInvoiceService;

@CrossOrigin
@RestController
@Component
@RequestMapping(value="/")
public class BillingInvoiceController {

	@Autowired
    private BillingInvoiceService billingInvoiceService;
	
	@Autowired
    private BillingContractService billingContractService;
	
	@RequestMapping(value ="/getInvoiceListByAgreementNum/{agreementNum}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<List<InvoiceDTO>> getInvoiceListByAgreementNum(@PathVariable("agreementNum") String strAgreementNum) throws Exception{
		return billingInvoiceService.getInvoiceListByAgreementNum(strAgreementNum);
	}
	
}
