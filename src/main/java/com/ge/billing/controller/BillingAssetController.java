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


import com.ge.billing.model.Asset;
import com.ge.billing.service.BillingAssetService;

@CrossOrigin
@RestController
@Component
@RequestMapping(value="/")
public class BillingAssetController {

	@Autowired
    private BillingAssetService billingAssetService;
	
	@RequestMapping(value ="/getAssetList/{strAssetNum}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Asset> getAssetDetailsByAssetNumber(@PathVariable("strAssetNum") String strAssetNum) throws Exception{
		return billingAssetService.getAssetDetailsByAssetNumber(strAssetNum);
	}
}
