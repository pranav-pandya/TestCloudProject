package com.ge.billing.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ge.billing.dto.ContractDTO;
import com.ge.billing.dto.InvoiceDTO;
import com.ge.billing.model.Contract;
import com.ge.billing.model.Invoice;
import com.ge.billing.repository.BillingContractRepository;
import com.ge.billing.repository.BillingInvoiceRepository;
import com.ge.billing.util.BillingUtility;
import com.ge.billing.util.DateFormatter;
import com.ge.billing.util.StringUtils;


@Service
public class BillingInvoiceService {
	
	@Autowired
	BillingInvoiceRepository billingInvoiceRepository;
	private MapperFactory invoiceMapperFactory = new DefaultMapperFactory.Builder().build();
	private BoundMapperFacade<Invoice, InvoiceDTO> invoiceBoundMapper = invoiceMapperFactory.getMapperFacade(Invoice.class, InvoiceDTO.class);

	@Autowired
	BillingContractRepository billingContractRepository;
	private MapperFactory contractMapperFactory = new DefaultMapperFactory.Builder().build();
	private BoundMapperFacade<Contract ,ContractDTO> contractBoundMapper = contractMapperFactory.getMapperFacade(Contract.class, ContractDTO.class);
	
	@Autowired
    private BillingContractService billingContractService;
	
	@Transactional
	public List<List<InvoiceDTO>> getInvoiceListByAgreementNum(String strAgreementNum) throws Exception{
		List<InvoiceDTO> billingInvoiceDTOObj = new ArrayList<InvoiceDTO>();
		List<List<InvoiceDTO>> retBillingInvoiceDTOObj = new ArrayList<List<InvoiceDTO>>();
		try {
			
			//Initializing local variables.
			String readingFromApplication = "smax.properties";
    		String defaultDate = "1900-01-01 00:00:00";
    		String stdDateFormat = "yyyy-MM-dd hh:mm:ss";
            String strInvoiceCycleStart = "";
            String strBillingFrequency = "";
            Timestamp tsStartDt = null;
            Timestamp tsEndDt = null;
            Timestamp tsNextInvoiceDt = null;
            java.sql.Date newNextInvoiceDateSql = null;
            List<String> newNextInvoiceDateList = null;
            List<InvoiceDTO> invoiceDTOList = null;
            List<ContractDTO> lineItemList = new ArrayList<ContractDTO>();
            List<Contract> conList = new ArrayList<Contract>();
            
            //STEP 1 & 2: Query for Eligible Agreement. 
            //Get all eligible contract list based on strAgreementNum; 
            if(null!=strAgreementNum){
            	conList = billingContractRepository.getContractListByAgreementNum(strAgreementNum);
            }else{
            	conList = billingContractRepository.getContractList();
            }
            System.out.println("getContractList size:"+conList.size());
            for (Object c : conList) {
	              Object[] contractObj = (Object[]) c;
	              
	              strAgreementNum = contractObj[0].toString();
	              tsStartDt = DateFormatter.convertStringDateToTimestamp(DateFormatter.chkForValidateDate(contractObj[1].toString(),defaultDate), stdDateFormat);
	              tsEndDt = DateFormatter.convertStringDateToTimestamp(DateFormatter.chkForValidateDate(contractObj[2].toString(),defaultDate), stdDateFormat);
	              tsNextInvoiceDt = DateFormatter.convertStringDateToTimestamp(DateFormatter.chkForValidateDate(contractObj[3].toString(),defaultDate), stdDateFormat);
	              strInvoiceCycleStart = StringUtils.replaceAllWhiteSpaces(contractObj[4].toString());
	              strBillingFrequency = StringUtils.replaceAllWhiteSpaces(contractObj[5].toString());
	              
	             //STEP 3: Calculate The new Next Invoice date
	             // Iterate on all eligible contract list. For getting newNextInvoiceDate List against for each agreement number 
	             newNextInvoiceDateList = BillingUtility.getNewNextInvoiceDateList(tsStartDt.toString(), tsNextInvoiceDt, tsEndDt.toString(), strInvoiceCycleStart, strBillingFrequency, readingFromApplication);
	              
	             //STEP 4 & 5: Generate the LineItems: 
                 if(null!=newNextInvoiceDateList){
                 	 String tempDate = (String)newNextInvoiceDateList.get(newNextInvoiceDateList.size()-1);
                 	 newNextInvoiceDateSql = DateFormatter.getSqlDate(tempDate,"yyyy-MM-dd hh:mm:ss.sss");
                 	 
                 	 // Query for Eligible Line items. Based on agreement number and newNextInvoiceDt  
                 	 lineItemList = billingContractService.getContractLineItemList(strAgreementNum, newNextInvoiceDateSql);
                 	 System.out.println("newNextInvoiceDateSql::"+tempDate);
                 	 System.out.println("lineItemList size::"+lineItemList.size());
                 }
                 
                //STEP 6: Get Invoice List based on lineItemList
                if(null!=lineItemList){
                 	for(ContractDTO contractLineItemDTO : lineItemList){
	                 		invoiceDTOList = BillingUtility.getInvoiceList(contractLineItemDTO);
	                 		System.out.println("\n\n::::----------------------------------------------------------------------");
	                 		System.out.println("Number of Invoices to be created for agreement number=" + strAgreementNum+" lineitemId= " + contractLineItemDTO.getLineItemId() + " invoiceDtoList:::::::::"+invoiceDTOList.size());
	                 		System.out.println("\n\n::::----------------------------------------------------------------------");
                 			//STEP 8: Save Invoice
                         	if(null!=invoiceDTOList){
                         	for(InvoiceDTO invoiceDTOObj : invoiceDTOList){
                         		billingInvoiceDTOObj = billingContractService.saveInvoiceData(invoiceDTOObj);
                    			retBillingInvoiceDTOObj.add(billingInvoiceDTOObj);
                         	}
                         	
                         	//STEP 9: Update ContractLineItem RC start Date
                         	if(null != billingInvoiceDTOObj){
	                         	Timestamp tsInvoiceEndDate = billingInvoiceDTOObj.get(billingInvoiceDTOObj.size()-1).getInvoiceEndDate();
	                         	tsInvoiceEndDate = BillingUtility.setReqDay(tsInvoiceEndDate,1);
	                         	Integer rcStartDateUpdateCount = billingContractRepository.updateRcStartDtByAgreementNumberandLineItemId(tsInvoiceEndDate, strAgreementNum, contractLineItemDTO.getLineItemId()); //TODO: strLineItemId
                         	}
                         }
                 	}
                }
                
                //STEP 10: UpdateContract based on agreement number and newNextInvoiceDt
                if(null!=invoiceDTOList && invoiceDTOList.size()>0){
                	Integer contractNextInvoiceDateUpdateCount = billingContractRepository.updateNxtNewInvoiceDtByAgreementNumber(newNextInvoiceDateSql, strAgreementNum);
                }
                
                //STEP N(last step): Release the variables.
                strInvoiceCycleStart = "";
                strBillingFrequency = "";
                tsStartDt = null;
                tsEndDt = null; 
                tsNextInvoiceDt = null;
                newNextInvoiceDateSql = null;
                newNextInvoiceDateList = null;
                invoiceDTOList = null;
            }
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return retBillingInvoiceDTOObj;
	}

}
