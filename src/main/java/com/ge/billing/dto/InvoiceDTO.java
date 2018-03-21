package com.ge.billing.dto;

import java.sql.*;

public class InvoiceDTO {
	
	private Integer invoiceId;

	private String affliateFolder;

	private String agreementItem;

	private String agreementLineItemId;

	private String agreementNum;

	private String agreementType;

	private double amount;

	private String assetLct;

	private String assetNum;

	private String billToAccountName;

	private String billToAddress;

	private String billToAddressLine2;

	private String billToAddressLine3;

	private String billToCity;

	private String billToCountry;

	private Integer billToCustomerNum;

	private String billToOperatingUnit;

	private Integer billToOracleSiteId;

	private String billToPostalCode;

	private String billToProvince;

	private Integer billToSiteNum;

	private String billToSiteUsageType;

	private String billToState;

	private String billingFrequence;

	private String billingSchedule;

	private String code;

	private String contactFaxNum;

	private String contactPhone;

	private String creditReason;

	private String creditRebillBackComments;

	private String currency;

	private String customerInvoiceNum;

	private String customerType;

	private String eftAch;

	private String eftWithdrawalDate;

	private String eqpCostCenter;

	private double expenseConcessionDiscount;

	private double expenseTotalCost;

	private double expenseTotalRevenue;

	private String externalComments;

	private String firstName;

	private String fmiFolder;

	private String fullCycleFlag;

	private String future1;

	private String future2;

	private String future3;

	private String future4;

	private String future5;

	private String future6;

	private String hfsReference;

	private Integer idnIdNum;

	private String intercompany;

	private String internalComments;

	private Timestamp invoiceDate;

	private Timestamp invoiceEndDate;

	private String invoiceNum;

	private Timestamp invoiceStartDate;

	private String invoiceType;

	private String invoiceZone;

	private String lastName;

	private String manual;

	private Integer marketSegment;

	private String materialClass;

	private Integer modality;

	private String modalityName;

	private double mrcAmount;

	private String operatingUnit;

	private String oracleInvoiceCreditNum;

	private Timestamp oracleInvoiceDate;

	private String oracleInvoiceStatus;

	private String orderNumber;

	private String originalInvoiceNum;

	private String pAndL;

	private double partConcessionDiscount;

	private double partTotalCost;

	private double partTotalRevenue;

	private String paymentTerm;

	private String pendingFixReason;

	private Timestamp poExpirationDate;

	private String poNum;

	private String poRequired;

	private String product;

	private double rcAmount;

	private String reason;

	private String refCreditRebillNum;

	private String requestType;

	private String scWorkflowNum;

	private String serviceRegion;

	private String shipToAccountName;

	private String shipToAddress;

	private String shipToAddressLine2;

	private String shipToAddressLine3;

	private String shipToCity;

	private String shipToCountry;

	private Integer shipToCustomerNum;

	private String shipToOperatingUnit;

	private Integer shipToOracleSiteId;

	private String shipToPostalCode;

	private String shipToProvince;

	private Integer shipToSiteNum;

	private String shipToSiteUsageType;

	private String shipToState;

	private String singleInvoice;

	private Timestamp srClosedDate;

	private String srCreatedBy;

	private String srNum;

	private Timestamp srOpenDate;

	private String srType;

	private String status;

	private String subStatusAr;

	private String subStatusGl;

	private String svcCostCenter;

	private String systemId;

	private double timeConcessionDiscount;

	private double timeTotalCost;

	private double timeTotalRevenue;

	private String transactionType;
	//added billing prorated
	
	//private String billingFrequency;
	
	private String invoiceCycleStart;
	
	private double preTaxAmount;
	private double taxAmount;
	
	public double getPreTaxAmount() {
		return preTaxAmount;
	}

	public void setPreTaxAmount(double preTaxAmount) {
		this.preTaxAmount = preTaxAmount;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getpAndL() {
		return pAndL;
	}

	public void setpAndL(String pAndL) {
		this.pAndL = pAndL;
	}

	/*public String getBillingFrequency() {
		return billingFrequency;
	}

	public void setBillingFrequency(String billingFrequency) {
		this.billingFrequency = billingFrequency;
	}*/

	public String getInvoiceCycleStart() {
		return invoiceCycleStart;
	}

	public void setInvoiceCycleStart(String invoiceCycleStart) {
		this.invoiceCycleStart = invoiceCycleStart;
	}

	private Integer revision=1;

	public InvoiceDTO() {
	}

	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getAffliateFolder() {
		return this.affliateFolder;
	}

	public void setAffliateFolder(String affliateFolder) {
		this.affliateFolder = affliateFolder;
	}

	public String getAgreementItem() {
		return this.agreementItem;
	}

	public void setAgreementItem(String agreementItem) {
		this.agreementItem = agreementItem;
	}

	public String getAgreementLineItemId() {
		return this.agreementLineItemId;
	}

	public void setAgreementLineItemid(String agreementLineItemId) {
		this.agreementLineItemId = agreementLineItemId;
	}

	public String getAgreementNum() {
		return this.agreementNum;
	}

	public void setAgreementNum(String agreementNum) {
		this.agreementNum = agreementNum;
	}

	public String getAgreementType() {
		return this.agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAssetLct() {
		return this.assetLct;
	}

	public void setAssetLct(String assetLct) {
		this.assetLct = assetLct;
	}

	public String getAssetNum() {
		return this.assetNum;
	}

	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}

	public String getBillToAccountName() {
		return this.billToAccountName;
	}

	public void setBillToAccountName(String billToAccountName) {
		this.billToAccountName = billToAccountName;
	}

	public String getBillToAddress() {
		return this.billToAddress;
	}

	public void setBillToAddress(String billToAddress) {
		this.billToAddress = billToAddress;
	}

	public String getBillToAddressLine2() {
		return this.billToAddressLine2;
	}

	public void setBillToAddressLine2(String billToAddressLine2) {
		this.billToAddressLine2 = billToAddressLine2;
	}

	public String getBillToAddressLine3() {
		return this.billToAddressLine3;
	}

	public void setBillToAddressLine3(String billToAddressLine3) {
		this.billToAddressLine3 = billToAddressLine3;
	}

	public String getBillToCity() {
		return this.billToCity;
	}

	public void setBillToCity(String billToCity) {
		this.billToCity = billToCity;
	}

	public String getBillToCountry() {
		return this.billToCountry;
	}

	public void setBillToCountry(String billToCountry) {
		this.billToCountry = billToCountry;
	}

	public Integer getBillToCustomerNum() {
		return this.billToCustomerNum;
	}

	public void setBillToCustomerNum(Integer billToCustomerNum) {
		this.billToCustomerNum = billToCustomerNum;
	}

	public String getBillToOperatingUnit() {
		return this.billToOperatingUnit;
	}

	public void setBillToOperatingUnit(String billToOperatingUnit) {
		this.billToOperatingUnit = billToOperatingUnit;
	}

	public Integer getBillToOracleSiteId() {
		return this.billToOracleSiteId;
	}

	public void setBillToOracleSiteId(Integer billToOracleSiteId) {
		this.billToOracleSiteId = billToOracleSiteId;
	}

	public String getBillToPostalCode() {
		return this.billToPostalCode;
	}

	public void setBillToPostalCode(String billToPostalCode) {
		this.billToPostalCode = billToPostalCode;
	}

	public String getBillToProvince() {
		return this.billToProvince;
	}

	public void setBillToProvince(String billToProvince) {
		this.billToProvince = billToProvince;
	}

	public Integer getBillToSiteNum() {
		return this.billToSiteNum;
	}

	public void setBillToSiteNum(Integer billToSiteNum) {
		this.billToSiteNum = billToSiteNum;
	}

	public String getBillToSiteUsageType() {
		return this.billToSiteUsageType;
	}

	public void setBillToSiteUsageType(String billToSiteUsageType) {
		this.billToSiteUsageType = billToSiteUsageType;
	}

	public String getBillToState() {
		return this.billToState;
	}

	public void setBillToState(String billToState) {
		this.billToState = billToState;
	}

	public String getBillingFrequence() {
		return this.billingFrequence;
	}

	public void setBillingFrequence(String billingFrequence) {
		this.billingFrequence = billingFrequence;
	}

	public String getBillingSchedule() {
		return this.billingSchedule;
	}

	public void setBillingSchedule(String billingSchedule) {
		this.billingSchedule = billingSchedule;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContactFaxNum() {
		return this.contactFaxNum;
	}

	public void setContactFaxNum(String contactFaxNum) {
		this.contactFaxNum = contactFaxNum;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getCreditReason() {
		return this.creditReason;
	}

	public void setCreditReason(String creditReason) {
		this.creditReason = creditReason;
	}

	public String getCreditRebillBackComments() {
		return this.creditRebillBackComments;
	}

	public void setCreditRebillBackComments(String creditRebillBackComments) {
		this.creditRebillBackComments = creditRebillBackComments;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomerInvoiceNum() {
		return this.customerInvoiceNum;
	}

	public void setCustomerInvoiceNum(String customerInvoiceNum) {
		this.customerInvoiceNum = customerInvoiceNum;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getEftAch() {
		return this.eftAch;
	}

	public void setEftAch(String eftAch) {
		this.eftAch = eftAch;
	}

	public String getEftWithdrawalDate() {
		return this.eftWithdrawalDate;
	}

	public void setEftWithdrawalDate(String eftWithdrawalDate) {
		this.eftWithdrawalDate = eftWithdrawalDate;
	}

	public String getEqpCostCenter() {
		return this.eqpCostCenter;
	}

	public void setEqpCostCenter(String eqpCostCenter) {
		this.eqpCostCenter = eqpCostCenter;
	}

	public double getExpenseConcessionDiscount() {
		return this.expenseConcessionDiscount;
	}

	public void setExpenseConcessionDiscount(double expenseConcessionDiscount) {
		this.expenseConcessionDiscount = expenseConcessionDiscount;
	}

	public double getExpenseTotalCost() {
		return this.expenseTotalCost;
	}

	public void setExpenseTotalCost(double expenseTotalCost) {
		this.expenseTotalCost = expenseTotalCost;
	}

	public double getExpenseTotalRevenue() {
		return this.expenseTotalRevenue;
	}

	public void setExpenseTotalRevenue(double expenseTotalRevenue) {
		this.expenseTotalRevenue = expenseTotalRevenue;
	}

	public String getExternalComments() {
		return this.externalComments;
	}

	public void setExternalComments(String externalComments) {
		this.externalComments = externalComments;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFmiFolder() {
		return this.fmiFolder;
	}

	public void setFmiFolder(String fmiFolder) {
		this.fmiFolder = fmiFolder;
	}

	public String getFullCycleFlag() {
		return this.fullCycleFlag;
	}

	public void setFullCycleFlag(String fullCycleFlag) {
		this.fullCycleFlag = fullCycleFlag;
	}

	public String getFuture1() {
		return this.future1;
	}

	public void setFuture1(String future1) {
		this.future1 = future1;
	}

	public String getFuture2() {
		return this.future2;
	}

	public void setFuture2(String future2) {
		this.future2 = future2;
	}

	public String getFuture3() {
		return this.future3;
	}

	public void setFuture3(String future3) {
		this.future3 = future3;
	}

	public String getFuture4() {
		return this.future4;
	}

	public void setFuture4(String future4) {
		this.future4 = future4;
	}

	public String getFuture5() {
		return this.future5;
	}

	public void setFuture5(String future5) {
		this.future5 = future5;
	}

	public String getFuture6() {
		return this.future6;
	}

	public void setFuture6(String future6) {
		this.future6 = future6;
	}

	public String getHfsReference() {
		return this.hfsReference;
	}

	public void setHfsReference(String hfsReference) {
		this.hfsReference = hfsReference;
	}

	public Integer getIdnIdNum() {
		return this.idnIdNum;
	}

	public void setIdnIdNum(Integer idnIdNum) {
		this.idnIdNum = idnIdNum;
	}

	public String getIntercompany() {
		return this.intercompany;
	}

	public void setIntercompany(String intercompany) {
		this.intercompany = intercompany;
	}

	public String getInternalComments() {
		return this.internalComments;
	}

	public void setInternalComments(String internalComments) {
		this.internalComments = internalComments;
	}

	public Timestamp getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Timestamp invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Timestamp getInvoiceEndDate() {
		return this.invoiceEndDate;
	}

	public void setInvoiceEndDate(Timestamp invoiceEndDate) {
		this.invoiceEndDate = invoiceEndDate;
	}

	public String getInvoiceNum() {
		return this.invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public Timestamp getInvoiceStartDate() {
		return this.invoiceStartDate;
	}

	public void setInvoiceStartDate(Timestamp invoiceStartDate) {
		this.invoiceStartDate = invoiceStartDate;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceZone() {
		return this.invoiceZone;
	}

	public void setInvoiceZone(String invoiceZone) {
		this.invoiceZone = invoiceZone;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getManual() {
		return this.manual;
	}

	public void setManual(String manual) {
		this.manual = manual;
	}

	public Integer getMarketSegment() {
		return this.marketSegment;
	}

	public void setMarketSegment(Integer marketSegment) {
		this.marketSegment = marketSegment;
	}

	public String getMaterialClass() {
		return this.materialClass;
	}

	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}

	public Integer getModality() {
		return this.modality;
	}

	public void setModality(Integer modality) {
		this.modality = modality;
	}

	public String getModalityName() {
		return this.modalityName;
	}

	public void setModalityName(String modalityName) {
		this.modalityName = modalityName;
	}

	public double getMrcAmount() {
		return this.mrcAmount;
	}

	public void setMrcAmount(double mrcAmount) {
		this.mrcAmount = mrcAmount;
	}

	public String getOperatingUnit() {
		return this.operatingUnit;
	}

	public void setOperatingUnit(String operatingUnit) {
		this.operatingUnit = operatingUnit;
	}

	public String getOracleInvoiceCreditNum() {
		return this.oracleInvoiceCreditNum;
	}

	public void setOracleInvoiceCreditNum(String oracleInvoiceCreditNum) {
		this.oracleInvoiceCreditNum = oracleInvoiceCreditNum;
	}

	public Timestamp getOracleInvoiceDate() {
		return this.oracleInvoiceDate;
	}

	public void setOracleInvoiceDate(Timestamp oracleInvoiceDate) {
		this.oracleInvoiceDate = oracleInvoiceDate;
	}

	public String getOracleInvoiceStatus() {
		return this.oracleInvoiceStatus;
	}

	public void setOracleInvoiceStatus(String oracleInvoiceStatus) {
		this.oracleInvoiceStatus = oracleInvoiceStatus;
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOriginalInvoiceNum() {
		return this.originalInvoiceNum;
	}

	public void setOriginalInvoiceNum(String originalInvoiceNum) {
		this.originalInvoiceNum = originalInvoiceNum;
	}

	public String getPAndL() {
		return this.pAndL;
	}

	public void setPAndL(String pAndL) {
		this.pAndL = pAndL;
	}

	public double getPartConcessionDiscount() {
		return this.partConcessionDiscount;
	}

	public void setPartConcessionDiscount(double partConcessionDiscount) {
		this.partConcessionDiscount = partConcessionDiscount;
	}

	public double getPartTotalCost() {
		return this.partTotalCost;
	}

	public void setPartTotalCost(double partTotalCost) {
		this.partTotalCost = partTotalCost;
	}

	public double getPartTotalRevenue() {
		return this.partTotalRevenue;
	}

	public void setPartTotalRevenue(double partTotalRevenue) {
		this.partTotalRevenue = partTotalRevenue;
	}

	public String getPaymentTerm() {
		return this.paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getPendingFixReason() {
		return this.pendingFixReason;
	}

	public void setPendingFixReason(String pendingFixReason) {
		this.pendingFixReason = pendingFixReason;
	}

	public Timestamp getPoExpirationDate() {
		return this.poExpirationDate;
	}

	public void setPoExpirationDate(Timestamp poExpirationDate) {
		this.poExpirationDate = poExpirationDate;
	}

	public String getPoNum() {
		return this.poNum;
	}

	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}

	public String getPoRequired() {
		return this.poRequired;
	}

	public void setPoRequired(String poRequired) {
		this.poRequired = poRequired;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public double getRcAmount() {
		return this.rcAmount;
	}

	public void setRcAmount(double rcAmount) {
		this.rcAmount = rcAmount;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRefCreditRebillNum() {
		return this.refCreditRebillNum;
	}

	public void setRefCreditRebillNum(String refCreditRebillNum) {
		this.refCreditRebillNum = refCreditRebillNum;
	}

	public String getRequestType() {
		return this.requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getScWorkflowNum() {
		return this.scWorkflowNum;
	}

	public void setScWorkflowNum(String scWorkflowNum) {
		this.scWorkflowNum = scWorkflowNum;
	}

	public String getServiceRegion() {
		return this.serviceRegion;
	}

	public void setServiceRegion(String serviceRegion) {
		this.serviceRegion = serviceRegion;
	}

	public String getShipToAccountName() {
		return this.shipToAccountName;
	}

	public void setShipToAccountName(String shipToAccountName) {
		this.shipToAccountName = shipToAccountName;
	}

	public String getShipToAddress() {
		return this.shipToAddress;
	}

	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public String getShipToAddressLine2() {
		return this.shipToAddressLine2;
	}

	public void setShipToAddressLine2(String shipToAddressLine2) {
		this.shipToAddressLine2 = shipToAddressLine2;
	}

	public String getShipToAddressLine3() {
		return this.shipToAddressLine3;
	}

	public void setShipToAddressLine3(String shipToAddressLine3) {
		this.shipToAddressLine3 = shipToAddressLine3;
	}

	public String getShipToCity() {
		return this.shipToCity;
	}

	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}

	public String getShipToCountry() {
		return this.shipToCountry;
	}

	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}

	public Integer getShipToCustomerNum() {
		return this.shipToCustomerNum;
	}

	public void setShipToCustomerNum(Integer shipToCustomerNum) {
		this.shipToCustomerNum = shipToCustomerNum;
	}

	public String getShipToOperatingUnit() {
		return this.shipToOperatingUnit;
	}

	public void setShipToOperatingUnit(String shipToOperatingUnit) {
		this.shipToOperatingUnit = shipToOperatingUnit;
	}

	public Integer getShipToOracleSiteId() {
		return this.shipToOracleSiteId;
	}

	public void setShipToOracleSiteId(Integer shipToOracleSiteId) {
		this.shipToOracleSiteId = shipToOracleSiteId;
	}

	public String getShipToPostalCode() {
		return this.shipToPostalCode;
	}

	public void setShipToPostalCode(String shipToPostalCode) {
		this.shipToPostalCode = shipToPostalCode;
	}

	public String getShipToProvince() {
		return this.shipToProvince;
	}

	public void setShipToProvince(String shipToProvince) {
		this.shipToProvince = shipToProvince;
	}

	public Integer getShipToSiteNum() {
		return this.shipToSiteNum;
	}

	public void setShipToSiteNum(Integer shipToSiteNum) {
		this.shipToSiteNum = shipToSiteNum;
	}

	public String getShipToSiteUsageType() {
		return this.shipToSiteUsageType;
	}

	public void setShipToSiteUsageType(String shipToSiteUsageType) {
		this.shipToSiteUsageType = shipToSiteUsageType;
	}

	public String getShipToState() {
		return this.shipToState;
	}

	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}

	public String getSingleInvoice() {
		return this.singleInvoice;
	}

	public void setSingleInvoice(String singleInvoice) {
		this.singleInvoice = singleInvoice;
	}

	public Timestamp getSrClosedDate() {
		return this.srClosedDate;
	}

	public void setSrClosedDate(Timestamp srClosedDate) {
		this.srClosedDate = srClosedDate;
	}

	public String getSrCreatedBy() {
		return this.srCreatedBy;
	}

	public void setSrCreatedBy(String srCreatedBy) {
		this.srCreatedBy = srCreatedBy;
	}

	public String getSrNum() {
		return this.srNum;
	}

	public void setSrNum(String srNum) {
		this.srNum = srNum;
	}

	public Timestamp getSrOpenDate() {
		return this.srOpenDate;
	}

	public void setSrOpenDate(Timestamp srOpenDate) {
		this.srOpenDate = srOpenDate;
	}

	public String getSrType() {
		return this.srType;
	}

	public void setSrType(String srType) {
		this.srType = srType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubStatusAr() {
		return this.subStatusAr;
	}

	public void setSubStatusAr(String subStatusAr) {
		this.subStatusAr = subStatusAr;
	}

	public String getSubStatusGl() {
		return this.subStatusGl;
	}

	public void setSubStatusGl(String subStatusGl) {
		this.subStatusGl = subStatusGl;
	}

	public String getSvcCostCenter() {
		return this.svcCostCenter;
	}

	public void setSvcCostCenter(String svcCostCenter) {
		this.svcCostCenter = svcCostCenter;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public double getTimeConcessionDiscount() {
		return this.timeConcessionDiscount;
	}

	public void setTimeConcessionDiscount(double timeConcessionDiscount) {
		this.timeConcessionDiscount = timeConcessionDiscount;
	}

	public double getTimeTotalCost() {
		return this.timeTotalCost;
	}

	public void setTimeTotalCost(double timeTotalCost) {
		this.timeTotalCost = timeTotalCost;
	}

	public double getTimeTotalRevenue() {
		return this.timeTotalRevenue;
	}

	public void setTimeTotalRevenue(double timeTotalRevenue) {
		this.timeTotalRevenue = timeTotalRevenue;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	@Override
	public String toString() {
		return "InvoiceDTO [invoiceId=" + invoiceId + ", affliateFolder="
				+ affliateFolder + ", agreementItem=" + agreementItem
				+ ", agreementLineItemId=" + agreementLineItemId
				+ ", agreementNum=" + agreementNum + ", agreementType="
				+ agreementType + ", amount=" + amount + ", assetLct="
				+ assetLct + ", assetNum=" + assetNum + ", billToAccountName="
				+ billToAccountName + ", billToAddress=" + billToAddress
				+ ", billToAddressLine2=" + billToAddressLine2
				+ ", billToAddressLine3=" + billToAddressLine3
				+ ", billToCity=" + billToCity + ", billToCountry="
				+ billToCountry + ", billToCustomerNum=" + billToCustomerNum
				+ ", billToOperatingUnit=" + billToOperatingUnit
				+ ", billToOracleSiteId=" + billToOracleSiteId
				+ ", billToPostalCode=" + billToPostalCode
				+ ", billToProvince=" + billToProvince + ", billToSiteNum="
				+ billToSiteNum + ", billToSiteUsageType="
				+ billToSiteUsageType + ", billToState=" + billToState
				+ ", billingFrequence=" + billingFrequence
				+ ", billingSchedule=" + billingSchedule + ", code=" + code
				+ ", contactFaxNum=" + contactFaxNum + ", contactPhone="
				+ contactPhone + ", creditReason=" + creditReason
				+ ", creditRebillBackComments=" + creditRebillBackComments
				+ ", currency=" + currency + ", customerInvoiceNum="
				+ customerInvoiceNum + ", customerType=" + customerType
				+ ", eftAch=" + eftAch + ", eftWithdrawalDate="
				+ eftWithdrawalDate + ", eqpCostCenter=" + eqpCostCenter
				+ ", expenseConcessionDiscount=" + expenseConcessionDiscount
				+ ", expenseTotalCost=" + expenseTotalCost
				+ ", expenseTotalRevenue=" + expenseTotalRevenue
				+ ", externalComments=" + externalComments + ", firstName="
				+ firstName + ", fmiFolder=" + fmiFolder + ", fullCycleFlag="
				+ fullCycleFlag + ", future1=" + future1 + ", future2="
				+ future2 + ", future3=" + future3 + ", future4=" + future4
				+ ", future5=" + future5 + ", future6=" + future6
				+ ", hfsReference=" + hfsReference + ", idnIdNum=" + idnIdNum
				+ ", intercompany=" + intercompany + ", internalComments="
				+ internalComments + ", invoiceDate=" + invoiceDate
				+ ", invoiceEndDate=" + invoiceEndDate + ", invoiceNum="
				+ invoiceNum + ", invoiceStartDate=" + invoiceStartDate
				+ ", invoiceType=" + invoiceType + ", invoiceZone="
				+ invoiceZone + ", lastName=" + lastName + ", manual=" + manual
				+ ", marketSegment=" + marketSegment + ", materialClass="
				+ materialClass + ", modality=" + modality + ", modalityName="
				+ modalityName + ", mrcAmount=" + mrcAmount
				+ ", operatingUnit=" + operatingUnit
				+ ", oracleInvoiceCreditNum=" + oracleInvoiceCreditNum
				+ ", oracleInvoiceDate=" + oracleInvoiceDate
				+ ", oracleInvoiceStatus=" + oracleInvoiceStatus
				+ ", orderNumber=" + orderNumber + ", originalInvoiceNum="
				+ originalInvoiceNum + ", pAndL=" + pAndL
				+ ", partConcessionDiscount=" + partConcessionDiscount
				+ ", partTotalCost=" + partTotalCost + ", partTotalRevenue="
				+ partTotalRevenue + ", paymentTerm=" + paymentTerm
				+ ", pendingFixReason=" + pendingFixReason
				+ ", poExpirationDate=" + poExpirationDate + ", poNum=" + poNum
				+ ", poRequired=" + poRequired + ", product=" + product
				+ ", rcAmount=" + rcAmount + ", reason=" + reason
				+ ", refCreditRebillNum=" + refCreditRebillNum
				+ ", requestType=" + requestType + ", scWorkflowNum="
				+ scWorkflowNum + ", serviceRegion=" + serviceRegion
				+ ", shipToAccountName=" + shipToAccountName
				+ ", shipToAddress=" + shipToAddress + ", shipToAddressLine2="
				+ shipToAddressLine2 + ", shipToAddressLine3="
				+ shipToAddressLine3 + ", shipToCity=" + shipToCity
				+ ", shipToCountry=" + shipToCountry + ", shipToCustomerNum="
				+ shipToCustomerNum + ", shipToOperatingUnit="
				+ shipToOperatingUnit + ", shipToOracleSiteId="
				+ shipToOracleSiteId + ", shipToPostalCode=" + shipToPostalCode
				+ ", shipToProvince=" + shipToProvince + ", shipToSiteNum="
				+ shipToSiteNum + ", shipToSiteUsageType="
				+ shipToSiteUsageType + ", shipToState=" + shipToState
				+ ", singleInvoice=" + singleInvoice + ", srClosedDate="
				+ srClosedDate + ", srCreatedBy=" + srCreatedBy + ", srNum="
				+ srNum + ", srOpenDate=" + srOpenDate + ", srType=" + srType
				+ ", status=" + status + ", subStatusAr=" + subStatusAr
				+ ", subStatusGl=" + subStatusGl + ", svcCostCenter="
				+ svcCostCenter + ", systemId=" + systemId
				+ ", timeConcessionDiscount=" + timeConcessionDiscount
				+ ", timeTotalCost=" + timeTotalCost + ", timeTotalRevenue="
				+ timeTotalRevenue + ", transactionType=" + transactionType
				+ ", invoiceCycleStart=" + invoiceCycleStart
				+ ", preTaxAmount=" + preTaxAmount + ", taxAmount=" + taxAmount
				+ ", revision=" + revision + "]";
	}

	
	
	

}
