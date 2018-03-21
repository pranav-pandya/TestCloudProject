package com.ge.billing.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	
	/*@GeneratedValue(strategy=GenerationType.IDENTITY)*/
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_id_seq_gen")
	@SequenceGenerator(name="invoice_id_seq_gen", sequenceName="invoice_id_seq",allocationSize=1)
	@Column(name="invoice_id")
	private Integer invoiceId;

	@Column(name="affliate_folder")
	private String affliateFolder;

	@Column(name="agreement_item")
	private String agreementItem;

	@Column(name="agreement_num")
	private String agreementNum;

	@Column(name="agreement_type")
	private String agreementType;

	private double amount;

	@Column(name="asset_lct")
	private String assetLct;

	@Column(name="asset_num")
	private String assetNum;

	@Column(name="bill_to_account_name")
	private String billToAccountName;

	@Column(name="bill_to_address")
	private String billToAddress;

	@Column(name="bill_to_address_line_2")
	private String billToAddressLine2;

	@Column(name="bill_to_address_line_3")
	private String billToAddressLine3;

	@Column(name="bill_to_city")
	private String billToCity;

	@Column(name="bill_to_country")
	private String billToCountry;

	@Column(name="bill_to_customer_num")
	private Integer billToCustomerNum;

	@Column(name="bill_to_operating_unit")
	private String billToOperatingUnit;

	@Column(name="bill_to_oracle_site_id")
	private Integer billToOracleSiteId;

	@Column(name="bill_to_postal_code")
	private String billToPostalCode;

	@Column(name="bill_to_province")
	private String billToProvince;

	@Column(name="bill_to_site_num")
	private Integer billToSiteNum;

	@Column(name="bill_to_site_usage_type")
	private String billToSiteUsageType;

	@Column(name="bill_to_state")
	private String billToState;

	@Column(name="billing_frequence")
	private String billingFrequence;

	@Column(name="billing_schedule")
	private String billingSchedule;

	private String code;

	@Column(name="contact_fax_num")
	private String contactFaxNum;

	@Column(name="contact_phone")
	private String contactPhone;

	@Column(name="credit_reason")
	private String creditReason;

	@Column(name="credit_rebill_back_comments")
	private String creditRebillBackComments;

	private String currency;

	@Column(name="customer_invoice_num")
	private String customerInvoiceNum;

	@Column(name="customer_type")
	private String customerType;

	@Column(name="eft_ach")
	private String eftAch;

	@Column(name="eft_withdrawal_date")
	private String eftWithdrawalDate;

	@Column(name="eqp_cost_center")
	private String eqpCostCenter;

	@Column(name="expense_concession_discount")
	private double expenseConcessionDiscount;

	@Column(name="expense_total_cost")
	private double expenseTotalCost;

	@Column(name="expense_total_revenue")
	private double expenseTotalRevenue;

	@Column(name="external_comments")
	private String externalComments;

	@Column(name="first_name")
	private String firstName;

	@Column(name="fmi_folder")
	private String fmiFolder;

	private String future1;

	private String future2;

	private String future3;

	private String future4;

	private String future5;

	private String future6;

	@Column(name="hfs_reference")
	private String hfsReference;

	@Column(name="idn_id_num")
	private Integer idnIdNum;

	private String intercompany;

	@Column(name="internal_comments")
	private String internalComments;

	@Column(name="invoice_date")
	private Timestamp invoiceDate;

	@Column(name="invoice_end_date")
	private Timestamp invoiceEndDate;

	@Column(name="invoice_num")
	private String invoiceNum;

	@Column(name="invoice_start_date")
	private Timestamp invoiceStartDate;

	@Column(name="invoice_type")
	private String invoiceType;

	@Column(name="invoice_zone")
	private String invoiceZone;

	@Column(name="last_name")
	private String lastName;

	@Column(name="market_segment")
	private Integer marketSegment;

	@Column(name="material_class")
	private String materialClass;

	private Integer modality;

	@Column(name="modality_name")
	private String modalityName;

	@Column(name="operating_unit")
	private String operatingUnit;

	@Column(name="oracle_invoice_credit_num")
	private String oracleInvoiceCreditNum;

	@Column(name="oracle_invoice_date")
	private Timestamp oracleInvoiceDate;

	@Column(name="oracle_invoice_status")
	private String oracleInvoiceStatus;

	@Column(name="original_invoice_num")
	private String originalInvoiceNum;

	@Column(name="p_and_l")
	private String pAndL;

	@Column(name="part_concession_discount")
	private double partConcessionDiscount;

	@Column(name="part_total_cost")
	private double partTotalCost;

	@Column(name="part_total_revenue")
	private double partTotalRevenue;

	@Column(name="payment_term")
	private String paymentTerm;

	@Column(name="po_expiration_date")
	private Timestamp poExpirationDate;

	@Column(name="po_num")
	private String poNum;

	@Column(name="po_required")
	private String poRequired;

	private String product;

	@Column(name="ref_credit_rebill_num")
	private String refCreditRebillNum;

	@Column(name="request_type")
	private String requestType;

	@Column(name="sc_workflow_num")
	private String scWorkflowNum;

	@Column(name="service_region")
	private String serviceRegion;

	@Column(name="ship_to_account_name")
	private String shipToAccountName;

	@Column(name="ship_to_address")
	private String shipToAddress;

	@Column(name="ship_to_address_line_2")
	private String shipToAddressLine2;

	@Column(name="ship_to_address_line_3")
	private String shipToAddressLine3;

	@Column(name="ship_to_city")
	private String shipToCity;

	@Column(name="ship_to_country")
	private String shipToCountry;

	@Column(name="ship_to_customer_num")
	private Integer shipToCustomerNum;

	@Column(name="ship_to_operating_unit")
	private String shipToOperatingUnit;

	@Column(name="ship_to_oracle_site_id")
	private Integer shipToOracleSiteId;

	@Column(name="ship_to_postal_code")
	private String shipToPostalCode;

	@Column(name="ship_to_province")
	private String shipToProvince;

	@Column(name="ship_to_site_num")
	private Integer shipToSiteNum;

	@Column(name="ship_to_site_usage_type")
	private String shipToSiteUsageType;

	@Column(name="ship_to_state")
	private String shipToState;

	@Column(name="single_invoice")
	private String singleInvoice;

	@Column(name="sr_closed_date")
	private Timestamp srClosedDate;

	@Column(name="sr_created_by")
	private String srCreatedBy;

	@Column(name="sr_num")
	private String srNum;

	@Column(name="sr_open_date")
	private Timestamp srOpenDate;

	@Column(name="sr_type")
	private String srType;

	private String status;

	@Column(name="sub_status_ar")
	private String subStatusAr;

	@Column(name="sub_status_gl")
	private String subStatusGl;

	@Column(name="svc_cost_center")
	private String svcCostCenter;

	@Column(name="system_id")
	private String systemId;

	@Column(name="time_concession_discount")
	private double timeConcessionDiscount;

	@Column(name="time_total_cost")
	private double timeTotalCost;

	@Column(name="time_total_revenue")
	private double timeTotalRevenue;

	@Column(name="transaction_type")
	private String transactionType;

	//bi-directional many-to-one association to InvoiceLineItem
	/*@OneToMany(mappedBy="invoice")
	private List<InvoiceLineItem> invoiceLineItems;
*/
	
	private String reason;

	@Column(name="order_number")
	private String orderNumber;

	
	@Column(name="pending_fix_reason")
	private String pendingFixReason;

	@Column(name="agreement_line_itemid")
	private String agreementLineItemId;

	@Column(name="rc_amount")
	private double rcAmount;
	
	@Column(name="mrc_amount")
	private double mrcAmount;
	
	
	@Column(name="full_cycle_flag")
	private char fullCycleFlag;
	
	private char manual;
	
	@Column(name="pre_tax_amount")
	private double preTaxAmount;

	@Column(name="tax_amount")
	private double taxAmount;
	

	public Invoice() {
		
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	
	public char getFullCycleFlag() {
		return fullCycleFlag;
	}

	public void setFullCycleFlag(char fullCycleFlag) {
		this.fullCycleFlag = fullCycleFlag;
	}

	public char getManual() {
		return manual;
	}

	public void setManual(char manual) {
		this.manual = manual;
	}

	public String getPendingFixReason() {
		return pendingFixReason;
	}

	public void setPendingFixReason(String pendingFixReason) {
		this.pendingFixReason = pendingFixReason;
	}

	public String getAgreementLineItemId() {
		return agreementLineItemId;
	}

	public void setAgreementLineItemId(String agreementLineItemId) {
		this.agreementLineItemId = agreementLineItemId;
	}

	public double getRcAmount() {
		return rcAmount;
	}

	public void setMrcAmount(double mrcAmount) {
		this.mrcAmount = mrcAmount;
	}

	public double getMrcAmount() {
		return mrcAmount;
	}

	public void setRcAmount(double rcAmount) {
		this.rcAmount = rcAmount;
	}

	public String getpAndL() {
		return pAndL;
	}

	public void setpAndL(String pAndL) {
		this.pAndL = pAndL;
	}

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

	
	/*public List<InvoiceLineItem> getInvoiceLineItems() {
		return this.invoiceLineItems;
	}

	public void setInvoiceLineItems(List<InvoiceLineItem> invoiceLineItems) {
		this.invoiceLineItems = invoiceLineItems;
	}

	public InvoiceLineItem addInvoiceLineItem(InvoiceLineItem invoiceLineItem) {
		getInvoiceLineItems().add(invoiceLineItem);
		invoiceLineItem.setInvoice(this);

		return invoiceLineItem;
	}

	public InvoiceLineItem removeInvoiceLineItem(InvoiceLineItem invoiceLineItem) {
		getInvoiceLineItems().remove(invoiceLineItem);
		invoiceLineItem.setInvoice(null);

		return invoiceLineItem;
	}*/

}