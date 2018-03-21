package com.ge.billing.dto;

import java.sql.Timestamp;

public class InvoiceLineDetailDTO {
	
	private String invoiceLineDetailsId;
	private String activityNum;
	private double actualBilledHours;
	private String arcExportedFlag;
	private String concessionDiscount;
	private double concessionDiscountAmount;
	private String concessionDiscountReason;
	private double cost;
	private String coverageSubType;
	private String coverageType;
	private Timestamp endTime;
	private String entitlementDiscId;
	private double entitlementDiscount;
	private String entitlementIdNonDisc;
	private String expenceNum;
	private String future1;
	private String future2;
	private String future3;
	private String future4;
	private String future5;
	private String future6;
	private String installedProdcut;
	private String invoiceLineId;
	private String invoiceNumber;
	private Timestamp laborEndDate;
	private Timestamp laborStartDate;
	private String liquidationFlag;
	private String minOrMax;
	private Integer mktSegment;
	private Integer modality;
	private double originalBalance;
	private String parentLineDetailId;
	private String partNum;
	private double partialWarrentyDiscount;
	private String pricingEntilementId;
	private Integer qunatity;
	private String rateType;
	private double remainingBalance;
	private String repairableFlag;
	private double revenue;
	private Timestamp startTime;
	private String timeNum;
	private double totalHours;
	private String trackerDetails;
	private double uboValue;
	private String weekDay;
	//bi-directional many-to-one association to InvoiceLineItem
	private InvoiceLineItemDTO invoiceLineItemDTO;

	public InvoiceLineDetailDTO() {
	}

	public String getInvoiceLineDetailsId() {
		return this.invoiceLineDetailsId;
	}

	public void setInvoiceLineDetailsId(String invoiceLineDetailsId) {
		this.invoiceLineDetailsId = invoiceLineDetailsId;
	}

	public String getActivityNum() {
		return this.activityNum;
	}

	public void setActivityNum(String activityNum) {
		this.activityNum = activityNum;
	}

	public double getActualBilledHours() {
		return this.actualBilledHours;
	}

	public void setActualBilledHours(double actualBilledHours) {
		this.actualBilledHours = actualBilledHours;
	}

	public String getArcExportedFlag() {
		return this.arcExportedFlag;
	}

	public void setArcExportedFlag(String arcExportedFlag) {
		this.arcExportedFlag = arcExportedFlag;
	}

	public String getConcessionDiscount() {
		return this.concessionDiscount;
	}

	public void setConcessionDiscount(String concessionDiscount) {
		this.concessionDiscount = concessionDiscount;
	}

	public double getConcessionDiscountAmount() {
		return this.concessionDiscountAmount;
	}

	public void setConcessionDiscountAmount(double concessionDiscountAmount) {
		this.concessionDiscountAmount = concessionDiscountAmount;
	}

	public String getConcessionDiscountReason() {
		return this.concessionDiscountReason;
	}

	public void setConcessionDiscountReason(String concessionDiscountReason) {
		this.concessionDiscountReason = concessionDiscountReason;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getCoverageSubType() {
		return this.coverageSubType;
	}

	public void setCoverageSubType(String coverageSubType) {
		this.coverageSubType = coverageSubType;
	}

	public String getCoverageType() {
		return this.coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getEntitlementDiscId() {
		return this.entitlementDiscId;
	}

	public void setEntitlementDiscId(String entitlementDiscId) {
		this.entitlementDiscId = entitlementDiscId;
	}

	public double getEntitlementDiscount() {
		return this.entitlementDiscount;
	}

	public void setEntitlementDiscount(double entitlementDiscount) {
		this.entitlementDiscount = entitlementDiscount;
	}

	public String getEntitlementIdNonDisc() {
		return this.entitlementIdNonDisc;
	}

	public void setEntitlementIdNonDisc(String entitlementIdNonDisc) {
		this.entitlementIdNonDisc = entitlementIdNonDisc;
	}

	public String getExpenceNum() {
		return this.expenceNum;
	}

	public void setExpenceNum(String expenceNum) {
		this.expenceNum = expenceNum;
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

	public String getInstalledProdcut() {
		return this.installedProdcut;
	}

	public void setInstalledProdcut(String installedProdcut) {
		this.installedProdcut = installedProdcut;
	}

	public String getInvoiceLineId() {
		return this.invoiceLineId;
	}

	public void setInvoiceLineId(String invoiceLineId) {
		this.invoiceLineId = invoiceLineId;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Timestamp getLaborEndDate() {
		return this.laborEndDate;
	}

	public void setLaborEndDate(Timestamp laborEndDate) {
		this.laborEndDate = laborEndDate;
	}

	public Timestamp getLaborStartDate() {
		return this.laborStartDate;
	}

	public void setLaborStartDate(Timestamp laborStartDate) {
		this.laborStartDate = laborStartDate;
	}

	public String getLiquidationFlag() {
		return this.liquidationFlag;
	}

	public void setLiquidationFlag(String liquidationFlag) {
		this.liquidationFlag = liquidationFlag;
	}

	public String getMinOrMax() {
		return this.minOrMax;
	}

	public void setMinOrMax(String minOrMax) {
		this.minOrMax = minOrMax;
	}

	public Integer getMktSegment() {
		return this.mktSegment;
	}

	public void setMktSegment(Integer mktSegment) {
		this.mktSegment = mktSegment;
	}

	public Integer getModality() {
		return this.modality;
	}

	public void setModality(Integer modality) {
		this.modality = modality;
	}

	public double getOriginalBalance() {
		return this.originalBalance;
	}

	public void setOriginalBalance(double originalBalance) {
		this.originalBalance = originalBalance;
	}

	public String getParentLineDetailId() {
		return this.parentLineDetailId;
	}

	public void setParentLineDetailId(String parentLineDetailId) {
		this.parentLineDetailId = parentLineDetailId;
	}

	public String getPartNum() {
		return this.partNum;
	}

	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}

	public double getPartialWarrentyDiscount() {
		return this.partialWarrentyDiscount;
	}

	public void setPartialWarrentyDiscount(double partialWarrentyDiscount) {
		this.partialWarrentyDiscount = partialWarrentyDiscount;
	}

	public String getPricingEntilementId() {
		return this.pricingEntilementId;
	}

	public void setPricingEntilementId(String pricingEntilementId) {
		this.pricingEntilementId = pricingEntilementId;
	}

	public Integer getQunatity() {
		return this.qunatity;
	}

	public void setQunatity(Integer qunatity) {
		this.qunatity = qunatity;
	}

	public String getRateType() {
		return this.rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public double getRemainingBalance() {
		return this.remainingBalance;
	}

	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public String getRepairableFlag() {
		return this.repairableFlag;
	}

	public void setRepairableFlag(String repairableFlag) {
		this.repairableFlag = repairableFlag;
	}

	public double getRevenue() {
		return this.revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public String getTimeNum() {
		return this.timeNum;
	}

	public void setTimeNum(String timeNum) {
		this.timeNum = timeNum;
	}

	public double getTotalHours() {
		return this.totalHours;
	}

	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	public String getTrackerDetails() {
		return this.trackerDetails;
	}

	public void setTrackerDetails(String trackerDetails) {
		this.trackerDetails = trackerDetails;
	}

	public double getUboValue() {
		return this.uboValue;
	}

	public void setUboValue(double uboValue) {
		this.uboValue = uboValue;
	}

	public String getWeekDay() {
		return this.weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public InvoiceLineItemDTO getInvoiceLineItem() {
		return this.invoiceLineItemDTO;
	}

	public void setInvoiceLineItemDTO(InvoiceLineItemDTO invoiceLineItemDTO) {
		this.invoiceLineItemDTO = invoiceLineItemDTO;
	}

}
