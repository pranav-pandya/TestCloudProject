package com.ge.billing.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the invoice_line_details database table.
 * 
 */
@Entity
public class InvoiceLineDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="invoice_line_details_id")
	private String invoiceLineDetailsId;

	@Column(name="activity_num")
	private String activityNum;

	@Column(name="actual_billed_hours")
	private double actualBilledHours;

	@Column(name="arc_exported_flag")
	private String arcExportedFlag;

	@Column(name="concession_discount")
	private String concessionDiscount;

	@Column(name="concession_discount_amount")
	private double concessionDiscountAmount;

	@Column(name="concession_discount_reason")
	private String concessionDiscountReason;

	private double cost;

	@Column(name="coverage_sub_type")
	private String coverageSubType;

	@Column(name="coverage_type")
	private String coverageType;

	@Column(name="end_time")
	private Timestamp endTime;

	@Column(name="entitlement_disc_id")
	private String entitlementDiscId;

	@Column(name="entitlement_discount")
	private double entitlementDiscount;

	@Column(name="entitlement_id_non_disc")
	private String entitlementIdNonDisc;

	@Column(name="expence_num")
	private String expenceNum;

	private String future1;

	private String future2;

	private String future3;

	private String future4;

	private String future5;

	private String future6;

	@Column(name="installed_prodcut")
	private String installedProdcut;

	@Column(name="invoice_line_id")
	private String invoiceLineId;

	@Column(name="invoice_number")
	private String invoiceNumber;

	@Column(name="labor_end_date")
	private Timestamp laborEndDate;

	@Column(name="labor_start_date")
	private Timestamp laborStartDate;

	@Column(name="liquidation_flag")
	private String liquidationFlag;

	@Column(name="min_or_max")
	private String minOrMax;

	@Column(name="mkt_segment")
	private Integer mktSegment;

	private Integer modality;

	@Column(name="original_balance")
	private double originalBalance;

	@Column(name="parent_line_detail_id")
	private String parentLineDetailId;

	@Column(name="part_num")
	private String partNum;

	@Column(name="partial_warrenty_discount")
	private double partialWarrentyDiscount;

	@Column(name="pricing_entilement_id")
	private String pricingEntilementId;

	private Integer qunatity;

	@Column(name="rate_type")
	private String rateType;

	@Column(name="remaining_balance")
	private double remainingBalance;

	@Column(name="repairable_flag")
	private String repairableFlag;

	private double revenue;

	@Column(name="start_time")
	private Timestamp startTime;

	@Column(name="time_num")
	private String timeNum;

	@Column(name="total_hours")
	private double totalHours;

	@Column(name="tracker_details")
	private String trackerDetails;

	@Column(name="ubo_value")
	private double uboValue;

	@Column(name="week_day")
	private String weekDay;

	//bi-directional many-to-one association to InvoiceLineItem
	@ManyToOne
	@JoinColumn(name="invoice_line_item_id")
	private InvoiceLineItem invoiceLineItem;

	public InvoiceLineDetail() {
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

	public InvoiceLineItem getInvoiceLineItem() {
		return this.invoiceLineItem;
	}

	public void setInvoiceLineItem(InvoiceLineItem invoiceLineItem) {
		this.invoiceLineItem = invoiceLineItem;
	}

}