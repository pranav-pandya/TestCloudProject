package com.ge.billing.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.billing.dto.AddressDetailDTO;
import com.ge.billing.dto.AssetDTO;
import com.ge.billing.dto.ContractDTO;
import com.ge.billing.dto.ContractErrorLogDTO;
import com.ge.billing.dto.CustomerAccountDTO;
import com.ge.billing.dto.InvoiceDTO;
import com.ge.billing.model.AddressDetail;
import com.ge.billing.model.Asset;
import com.ge.billing.model.Contract;
import com.ge.billing.model.CustomerAccount;
import com.ge.billing.model.Invoice;
import com.ge.billing.repository.BillingAddressDetailRepository;
import com.ge.billing.repository.BillingAssetRepository;
import com.ge.billing.repository.BillingContractRepository;
import com.ge.billing.repository.BillingCustomerAccountRepository;
import com.ge.billing.repository.BillingInvoiceRepository;
import com.ge.billing.util.BillingUtility;
import com.ge.billing.util.DateFormatter;
import com.ge.billing.util.StringUtils;

/**
 * @version 1.0
 * @author 502692964
 * The BillingContractService class is responsible for all Billing Contract Service Scheduler.
 */
@Service
public class BillingContractService {

	@Autowired
	BillingContractErrorLogService billingContractErrorLogService;

	
	@Autowired
	BillingContractRepository billingContractRepository;
	private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	private BoundMapperFacade<Contract ,ContractDTO> boundMapper = mapperFactory.getMapperFacade(Contract.class, ContractDTO.class);
	
	@Autowired
	BillingAddressDetailRepository billingAddressDetailRepository;
	private MapperFactory addressDetailMapperFactory = new DefaultMapperFactory.Builder().build();
	private BoundMapperFacade<AddressDetail ,AddressDetailDTO> addressDetailBoundMapper = addressDetailMapperFactory.getMapperFacade(AddressDetail.class, AddressDetailDTO.class);
	
	@Autowired
	BillingAssetRepository billingAssetRepository;
	private MapperFactory assetMapperFactory = new DefaultMapperFactory.Builder().build();
	private BoundMapperFacade<Asset, AssetDTO> assetBoundMapper = assetMapperFactory.getMapperFacade(Asset.class, AssetDTO.class);

	@Autowired
	BillingInvoiceRepository billingInvoiceRepository;
	private MapperFactory invoiceMapperFactory = new DefaultMapperFactory.Builder().build();
	private BoundMapperFacade<Invoice, InvoiceDTO> invoiceBoundMapper = invoiceMapperFactory.getMapperFacade(Invoice.class, InvoiceDTO.class);
	
	@Autowired
	BillingCustomerAccountRepository billingCustomerAccountRepository;
	private MapperFactory customerAccountMapperFactory = new DefaultMapperFactory.Builder().build();
	private BoundMapperFacade<CustomerAccount, CustomerAccountDTO> customerAccountBoundMapper = customerAccountMapperFactory.getMapperFacade(CustomerAccount.class, CustomerAccountDTO.class);
	
	/**
	 * @metohdname getContractList
	 * @param strAgreementNum of String data type
	 * @return List of ContractDTO type
	 */
	
	
	public List<ContractDTO> getContractList(String strAgreementNum) {
        List<Contract> conList = new ArrayList<Contract>();
        List<ContractDTO> conDtoList = new ArrayList<ContractDTO>();
        List<ContractDTO> lineItemList = new ArrayList<ContractDTO>();
        List<InvoiceDTO> billingInvoiceDTOObj = new ArrayList<InvoiceDTO>();
        List<List<InvoiceDTO>> retBillingInvoiceDTOObj = new ArrayList<List<InvoiceDTO>>();
        
        
        		String readingFromApplication = "smax.properties";
        		String defaultDate = "1900-01-01 00:00:00";
        		String stdDateFormat = "yyyy-MM-dd hh:mm:ss";
        		
        		java.sql.Date newNextInvoiceDateSql = null;
	            Timestamp tsStartDt = null;
	            Timestamp tsEndDt = null;
	            Timestamp tsNextInvoiceDt = null;
	            String strInvoiceCycleStart = "";
	            String strBillingFrequency = "";
	            List<String> newNextInvoiceDateList = null;
	            
	            
	            List<InvoiceDTO> invoiceDTOList=null;
	           
        	   //Step 1 & 2: Get all eligible contract list;
               if(null!=strAgreementNum){
            	   try{
            		   conList = billingContractRepository.getContractListByAgreementNum(strAgreementNum);
            		 //  strAgreementNum=null;
            	   }
            	   catch(Exception e)
            	   {

       				int i=logException(strAgreementNum,new Timestamp(System.currentTimeMillis()),1,
       						e.getClass().getSimpleName()+" Method Name: getContractListByAgreementNum(strAgreementNum) File Name: billingContractRepository",new Timestamp(System.currentTimeMillis()),1,null,
        					null,null,"Y");
        			System.out.println("1) Error log saved:"+i);
            	   }
            	   
               }else
               {
            	   try{
            			conList = billingContractRepository.getContractList();
            		//	strAgreementNum=null;
            	   }
            	   catch(Exception e)
            	   {
            		
       				int i=logException(strAgreementNum,new Timestamp(System.currentTimeMillis()),1,
       						e.getClass().getSimpleName()+" Method Name: getContractList() File Name: billingContractRepository",new Timestamp(System.currentTimeMillis()),1,null,
        					null,null,"Y");
        			System.out.println("2) Error log saved:"+i);
            	   }
               
               }
               
               
               for (Object c : conList) {
            	   
            	   try{
            		   
            		
                     Object[] contractObj = (Object[]) c;
                     
                     strAgreementNum = StringUtils.checkNullString(contractObj[0].toString());
                     tsStartDt = DateFormatter.convertStringDateToTimestamp(DateFormatter.chkForValidateDate(StringUtils.checkNullString(contractObj[1].toString()),defaultDate), stdDateFormat);
                     tsEndDt = DateFormatter.convertStringDateToTimestamp(DateFormatter.chkForValidateDate(StringUtils.checkNullString(contractObj[2].toString()),defaultDate), stdDateFormat);
                     tsNextInvoiceDt = DateFormatter.convertStringDateToTimestamp(DateFormatter.chkForValidateDate(StringUtils.checkNullString(contractObj[3].toString()),defaultDate), stdDateFormat);
                     strInvoiceCycleStart = StringUtils.replaceAllWhiteSpaces(StringUtils.checkNullString(contractObj[4].toString()));
                     strBillingFrequency = StringUtils.replaceAllWhiteSpaces(StringUtils.checkNullString(contractObj[5].toString()));
                
		      //Step 3: Iterate on all eligible contract list. For getting newNextInvoiceDate List against for each aggrement_number
                    
                     newNextInvoiceDateList = BillingUtility.getNewNextInvoiceDateList(tsStartDt.toString(), tsNextInvoiceDt, tsEndDt.toString(), strInvoiceCycleStart, strBillingFrequency, readingFromApplication);
                     System.out.println("newNextInvoiceDateList====================>"+newNextInvoiceDateList);
                     
                     //Step 4 & 5: Generate the LineItems: 
                     if(null!=newNextInvoiceDateList){
                    	 String strNewNextInvoiceLastDate = (String)newNextInvoiceDateList.get(newNextInvoiceDateList.size()-1);
                    	 newNextInvoiceDateSql = DateFormatter.getSqlDate(strNewNextInvoiceLastDate,"yyyy-MM-dd hh:mm:ss.sss");
                    	
                    	 
                    	 lineItemList = this.getContractLineItemList(strAgreementNum, newNextInvoiceDateSql);
                    	                     	
                    
                     }
                     
                    //Step 6: Get Invoice List based on //lineItemList......
                    if(null!=lineItemList){
                    	for(ContractDTO contractLineItemDTO : lineItemList){
                    		//Step 7: Generate Invoice List based on rc_start_Dt, BILLING_FREQUENCY, INVOICE_CYCLE_START, charge_end_dt 
				
                    		try
                    		{
                    			invoiceDTOList = BillingUtility.getInvoiceList(contractLineItemDTO, readingFromApplication);
                    		}
                    		catch(Exception e)
                    		{
                    			int i=logException(strAgreementNum,new Timestamp(System.currentTimeMillis()),1,
                    					e.getClass().getSimpleName()+" Method Name: getInvoiceList(contractLineItemDTO) File Name: BillingUtility",new Timestamp(System.currentTimeMillis()),1,contractLineItemDTO.getLineItemId(),
                    					null,new Timestamp(contractLineItemDTO.getRcStartDt().getTime()),"Y");
                    			System.out.println("3) Error log saved:"+i);
                    		}
                    	
				//Step 8: DONE::Save Invoice
                            if(null!=invoiceDTOList){
                            	 List<InvoiceDTO> invoiceAmountList = BillingUtility.getInvoiceAmount(invoiceDTOList, readingFromApplication);
                            	 for(InvoiceDTO invoiceDTOObj : invoiceAmountList){
                            		 billingInvoiceDTOObj = saveInvoiceData(invoiceDTOObj);
                            		 retBillingInvoiceDTOObj.add(billingInvoiceDTOObj);
                            	 }
                            	 
                            	//STEP 9: Update ContractLineItem RC start Date
                            	 if(null != billingInvoiceDTOObj){
                                     Timestamp tsInvoiceEndDate = billingInvoiceDTOObj.get(billingInvoiceDTOObj.size()-1).getInvoiceEndDate();
                                     long tsInvoiceEndDateMills = tsInvoiceEndDate.getTime();
                                     
                                     //When updating rc_start_date for a contract line item, if the charge_end_date is less than the update charge_end_date as rc_start_date
                                     if(contractLineItemDTO.getChargeEndDt().getTime() <= tsInvoiceEndDateMills){
                                            //rcStartDate is greater than chargeEndDate
                                            tsInvoiceEndDate = contractLineItemDTO.getChargeEndDt();
                                     }else{
                                            tsInvoiceEndDate = BillingUtility.setReqDay(tsInvoiceEndDate,1);
                                     }
                                     try
                                     {
                                    	 Integer rcStartDateUpdateCount = billingContractRepository.updateRcStartDtByAgreementNumberandLineItemId(tsInvoiceEndDate, strAgreementNum, contractLineItemDTO.getLineItemId()); //TODO: strLineItemId
                                     }
                                     catch(Exception e)
                                     {
                            				
                            				int i=logException(strAgreementNum,new Timestamp(System.currentTimeMillis()),1,
                            						e.getClass().getSimpleName()+" Method Name: updateRcStartDtByAgreementNumberandLineItemId(tsInvoiceEndDate, strAgreementNum, contractLineItemDTO.getLineItemId()) File Name: billingContractRepository",new Timestamp(System.currentTimeMillis()),1,contractLineItemDTO.getLineItemId(),
                                					null,null,"Y");
                                			System.out.println("4) Error log saved:"+i);
                                    }
                                 }
                            }
                            conDtoList.add(contractLineItemDTO); //Manoj added for displaying on UI-browser
                    	}
                    }
                     
                    
                    //STEP 10: UpdateContract based on agreement number and newNextInvoiceDt
                    if(null!=invoiceDTOList && invoiceDTOList.size()>0){
                    	try
                    	{
                    		Integer contractNextInvoiceDateUpdateCount = billingContractRepository.updateNxtNewInvoiceDtByAgreementNumber(newNextInvoiceDateSql, strAgreementNum);
                    	}
                    	catch(Exception e)
                    	{
                    		
            				
            				int i=logException(strAgreementNum,new Timestamp(System.currentTimeMillis()),1,
            						e.getClass().getSimpleName()+" Method Name: updateNxtNewInvoiceDtByAgreementNumber(newNextInvoiceDateSql, strAgreementNum) File Name: billingContractRepository",new Timestamp(System.currentTimeMillis()),1,null,
                					null,null,"Y");
                			System.out.println("5) Error log saved:"+i);
                    	}
                    }
                    
                    
                  
                    
                    //Step 11: Release all resources or clear all the variables/resources
                    strAgreementNum =null;
                    billingInvoiceDTOObj = null;
                    strInvoiceCycleStart = "";
                    strBillingFrequency = null;
                    tsStartDt = null;
                    tsEndDt = null; 
                    tsNextInvoiceDt = null;
                    newNextInvoiceDateSql = null;
                    newNextInvoiceDateList = null;
                    invoiceDTOList = null;
              
               }
               
               catch(Exception e)
               {
            	   e.getCause();
            	  e.getStackTrace();
  
       			int i=logException(strAgreementNum,new Timestamp(System.currentTimeMillis()),1,
       					e.getClass().getSimpleName()+" Invalid Data Entered",new Timestamp(System.currentTimeMillis()),1,null,
    					null,null,"Y");
    			System.out.println("6) Error log saved:"+i);
       			
       			
               }
            	  
               }//For loop ending here
       		 
        
        return conDtoList;
	}
	
	/**
	 * @metohdname getContractLineItemList
	 * @param agreementNum of String data type
	 * @param newNextInvoiceDate of date data type
	 * @return List of ContractDTO type
	 */
	public List<ContractDTO> getContractLineItemList(String agreementNum, Date newNextInvoiceDate)  {
		List<Contract> conList = new ArrayList<Contract>();
		List<ContractDTO> conDtoList = new ArrayList<ContractDTO>();
		
		try {
			
			conList=billingContractRepository.getContractLineItemList(agreementNum, newNextInvoiceDate);
			
		} catch (Exception e) 
		{
			
			int i=logException(agreementNum,new Timestamp(System.currentTimeMillis()),1,
					e.getClass().getSimpleName()+" Method Name: getContractLineItemList File Name: billingContractRepository",new Timestamp(System.currentTimeMillis()),1,null,
					new Timestamp(newNextInvoiceDate.getTime()),null,"Y");
			System.out.println("7) Error log saved:"+i);
		
		}
		
		for (Object c : conList) {
			ContractDTO conlst = null;
			
			try{
				 conlst = boundMapper.map((Contract)c);
				conDtoList.add(conlst);
				}catch(Exception e)
				{
					int i=logException(agreementNum,new Timestamp(System.currentTimeMillis()),1,
							e.getClass().getSimpleName()+"Invalid data entered",new Timestamp(System.currentTimeMillis()),1,null,
							new Timestamp(newNextInvoiceDate.getTime()),(null!=conlst?new Timestamp(conlst.getRcStartDt().getTime()):null),"Y");
					System.out.println("8) Error log saved:"+i);
				}		
		}
		return conDtoList;
	}
	
	
	public List<InvoiceDTO> saveInvoiceData(InvoiceDTO invoiceDTO){
		List<InvoiceDTO> invoiceDtoList = new ArrayList<InvoiceDTO>();
		
		
			
				String strAssetNum = StringUtils.checkNullString(invoiceDTO.getAssetNum());
				int billToId = invoiceDTO.getBillToSiteNum(); 
				int shipToId = invoiceDTO.getShipToSiteNum();
				int billToAcctNum = invoiceDTO.getBillToCustomerNum();
			
				List<CustomerAccount> customerAccountList = new ArrayList<CustomerAccount>();
				List<Asset> assetList = new ArrayList<Asset>();
				List<AddressDetail> billToAddressDetailList = new ArrayList<AddressDetail>();
				List<AddressDetail> shipToAddressDetailList = new ArrayList<AddressDetail>();
			
				try {
					
					customerAccountList = billingCustomerAccountRepository.getCustmomerAcctTypeByBillToNum(billToAcctNum);
				
				} catch (Exception e) {
					
					int i=logException(invoiceDTO.getAgreementNum(),new Timestamp(System.currentTimeMillis()),1,
							e.getClass().getSimpleName()+" Method Name: getCustmomerAcctTypeByBillToNum(billToAcctNum) File Name: billingCustomerAccountRepository",new Timestamp(System.currentTimeMillis()),1,invoiceDTO.getAgreementLineItemId(),
							invoiceDTO.getInvoiceStartDate(),invoiceDTO.getInvoiceStartDate(),"Y");
							System.out.println("9) Error log saved:"+i);
				
				}
				for(CustomerAccount customerAccountObj : customerAccountList){
					invoiceDTO.setCustomerType(customerAccountObj.getCustAcctType());
				}
				
				try {
				assetList = billingAssetRepository.getAssetDetailsByAssetNumber(strAssetNum);
				} catch (Exception e) {
					
					int i=logException(invoiceDTO.getAgreementNum(),new Timestamp(System.currentTimeMillis()),1,
							e.getClass().getSimpleName()+" Method Name: getAssetDetailsByAssetNumber(strAssetNum) File Name: billingAssetRepository",new Timestamp(System.currentTimeMillis()),1,invoiceDTO.getAgreementLineItemId(),
							invoiceDTO.getInvoiceStartDate(),invoiceDTO.getInvoiceStartDate(),"Y");
							System.out.println("10) Error log saved:"+i);
				
				}
				for(Asset assetObj : assetList){
					invoiceDTO.setSystemId(assetObj.getSystemId());
					invoiceDTO.setOperatingUnit(assetObj.getOperatingUnit());
					invoiceDTO.setServiceRegion(assetObj.getServiceRegion());
					invoiceDTO.setInvoiceZone(assetObj.getZone());
					invoiceDTO.setPAndL(assetObj.getPlText());
					invoiceDTO.setProduct(assetObj.getProduct());
					invoiceDTO.setMaterialClass(assetObj.getMaterialClass());
					invoiceDTO.setModality(assetObj.getModality());
					invoiceDTO.setModalityName(assetObj.getModalityName());
					invoiceDTO.setMarketSegment(assetObj.getMarketSegmet());
					invoiceDTO.setSystemId(assetObj.getSystemId());
					//TODO: Not found in table ????  invoiceDTO.setDeptCostCenter(a.getDeptCostCenter());
				}
				try {
					billToAddressDetailList = billingAddressDetailRepository.getAddressDetailsForBillToAndShipTo("Bill To", billToId);
					} catch (Exception e) {
					
					int i=logException(invoiceDTO.getAgreementNum(),new Timestamp(System.currentTimeMillis()),1,
							e.getClass().getSimpleName()+" Method Name: getAddressDetailsForBillToAndShipTo('Bill To', billToId) File Name: billingAddressDetailRepository",new Timestamp(System.currentTimeMillis()),1,invoiceDTO.getAgreementLineItemId(),
							invoiceDTO.getInvoiceStartDate(),invoiceDTO.getInvoiceStartDate(),"Y");
							System.out.println("11) Error log saved:"+i);
				
				}
				
				for(AddressDetail addrDetailBillTo : billToAddressDetailList){
					/*invoiceDTO.setBillToOracleSiteId(addrDetailBillTo.getOracleSiteId());*/
					invoiceDTO.setBillToOracleSiteId(null);
					invoiceDTO.setBillToSiteUsageType(addrDetailBillTo.getSiteUsgType());
					invoiceDTO.setBillToOperatingUnit(addrDetailBillTo.getOperatingUnit());
					invoiceDTO.setBillToAddress(addrDetailBillTo.getAddress());
					invoiceDTO.setBillToAddressLine2(addrDetailBillTo.getAddressLine2());
					invoiceDTO.setBillToAddressLine3(addrDetailBillTo.getAddressLine3());
					invoiceDTO.setBillToCity(addrDetailBillTo.getCity());
					invoiceDTO.setBillToState(addrDetailBillTo.getState());
					invoiceDTO.setBillToProvince(addrDetailBillTo.getProvince());
					invoiceDTO.setBillToPostalCode(addrDetailBillTo.getZipPostalCode());
					invoiceDTO.setBillToCountry(addrDetailBillTo.getCountry());

					//For AffliateFolder
					invoiceDTO.setAffliateFolder(getInvoiceAffiliateFolder(addrDetailBillTo.getAffiliateFolder()));
				}
				try {
			    shipToAddressDetailList = billingAddressDetailRepository.getAddressDetailsForBillToAndShipTo("Ship To", shipToId);
				} catch (Exception e) {
					
					int i=logException(invoiceDTO.getAgreementNum(),new Timestamp(System.currentTimeMillis()),1,
							e.getClass().getSimpleName()+" Method Name: getAddressDetailsForBillToAndShipTo('Ship To', shipToId) File Name:  billingAddressDetailRepository",new Timestamp(System.currentTimeMillis()),1,invoiceDTO.getAgreementLineItemId(),
							invoiceDTO.getInvoiceStartDate(),invoiceDTO.getInvoiceStartDate(),"Y");
							System.out.println("12) Error log saved:"+i);
				
				}
			    
			    for(AddressDetail addrDetailShipTo : shipToAddressDetailList){
			    	/*invoiceDTO.setShipToOracleSiteId(addrDetailShipTo.getOracleSiteId());*/
			    	invoiceDTO.setShipToOracleSiteId(null);
			    	invoiceDTO.setShipToSiteUsageType(addrDetailShipTo.getSiteUsgType());
			    	invoiceDTO.setShipToOperatingUnit(addrDetailShipTo.getOperatingUnit());
			    	invoiceDTO.setShipToAddress(addrDetailShipTo.getAddress());
			    	invoiceDTO.setShipToAddressLine2(addrDetailShipTo.getAddressLine2());
			    	invoiceDTO.setShipToAddressLine3(addrDetailShipTo.getAddressLine3());
			    	invoiceDTO.setShipToCity(addrDetailShipTo.getCity());
			    	invoiceDTO.setShipToState(addrDetailShipTo.getState());
			    	invoiceDTO.setShipToProvince(addrDetailShipTo.getProvince());
			    	invoiceDTO.setShipToPostalCode(addrDetailShipTo.getZipPostalCode());
			    	invoiceDTO.setShipToCountry(addrDetailShipTo.getCountry());
				}
			    
    			//Setting default values:
			    
			    invoiceDTO.setReason(null);
			    invoiceDTO.setOrderNumber(null);
			    invoiceDTO.setManual("N");
			    invoiceDTO.setPendingFixReason(null);
			    invoiceDTO.setSubStatusAr(null);
			    invoiceDTO.setSubStatusGl(null);
			    invoiceDTO.setOriginalInvoiceNum(null);
			    invoiceDTO.setRefCreditRebillNum(null);
			    invoiceDTO.setOracleInvoiceCreditNum(null);
			    invoiceDTO.setOracleInvoiceDate(null);
			    invoiceDTO.setOracleInvoiceStatus(null);
			    invoiceDTO.setCustomerInvoiceNum(null);
			    
			    
			    invoiceDTO.setStatus("Accepted");
			    invoiceDTO.setInvoiceType("Contract");
			    invoiceDTO.setTransactionType("Invoice");
			    invoiceDTO.setInvoiceDate(new Timestamp(System.currentTimeMillis()));
			    
			    
				invoiceDtoList.add(invoiceDTO);
							
				//save logic for invoice table
				for(InvoiceDTO invDTOObj : invoiceDtoList){
					Invoice inv = invoiceBoundMapper.mapReverse(invDTOObj); //From InvoiceDTO to Invoice 
					try{
						Invoice tempInvoiceObj = billingInvoiceRepository.save(inv);
						tempInvoiceObj.setInvoiceNum(BillingUtility.generateInvoiceNumber(Integer.toHexString(tempInvoiceObj.getInvoiceId()).toUpperCase(),10));
						billingInvoiceRepository.save(tempInvoiceObj);
						tempInvoiceObj= null;
					}
					catch(Exception e)
					{
						
					int i=logException(inv.getAgreementNum(),new Timestamp(System.currentTimeMillis()),1,
							e.getClass().getSimpleName()+" Method Name:save(inv) File Name:billingInvoiceRepository",new Timestamp(System.currentTimeMillis()),1,inv.getAgreementLineItemId(),
						inv.getInvoiceStartDate(),inv.getInvoiceStartDate(),"Y");
						System.out.println("13) Error log saved:"+i);
					}
					
				}
				billingInvoiceRepository.flush();
				
		
		return invoiceDtoList;
	}
	
	
	/**
	 * @metohdname getInvoiceAffiliateFolder
	 * @param strAffiliateFolder of String data type
	 * @return InvoiceAffiliateFolder of String data type
	 */
	public String getInvoiceAffiliateFolder(String strAffiliateFolder){
		try {
			if(null!=strAffiliateFolder && strAffiliateFolder.length()>=15){
				strAffiliateFolder = strAffiliateFolder.substring(strAffiliateFolder.length()-15, strAffiliateFolder.length()-8);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return strAffiliateFolder;
	}
	
	
	public int logException(String agreementNum, Timestamp createdUserDate,Integer createdUserId, String errorMessage, Timestamp lastUpdatedUserDate,Integer lastUpdatedUserId, String lineItemNum,Timestamp nextInvoiceDate,Timestamp rcStartDate,String statusFlag)
	{
		ContractErrorLogDTO contractErrorLogDTO =new ContractErrorLogDTO();
		contractErrorLogDTO.setAgreementNum(agreementNum);
		contractErrorLogDTO.setCreatedUserDate(createdUserDate);
		contractErrorLogDTO.setCreatedUserId(createdUserId);
		contractErrorLogDTO.setErrorMessage(errorMessage);
		contractErrorLogDTO.setLastUpdatedUserDate(lastUpdatedUserDate);
		contractErrorLogDTO.setLastUpdatedUserId(lastUpdatedUserId);
		contractErrorLogDTO.setLineItemNum(lineItemNum);
		contractErrorLogDTO.setNextInvoiceDate(nextInvoiceDate);
		contractErrorLogDTO.setRcStartDate(rcStartDate);
		contractErrorLogDTO.setStatusFlag(statusFlag);
		return billingContractErrorLogService.logException(contractErrorLogDTO);
		
	}
	
	
	
}
