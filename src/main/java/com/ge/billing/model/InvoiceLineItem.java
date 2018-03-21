package com.ge.billing.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the invoice_line_item database table.
 * 
 */
@Entity
public class InvoiceLineItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="invoice_line_item_id")
	private String invoiceLineItemId;

	@Column(name="activity_billable")
	private String activityBillable;

	@Column(name="activity_id")
	private String activityId;

	@Column(name="activity_type")
	private String activityType;

	@Column(name="asset_num")
	private String assetNum;

	private double cost;

	@Column(name="employee_lct")
	private String employeeLct;

	@Column(name="employee_ou")
	private String employeeOu;

	@Column(name="employee_sso")
	private String employeeSso;

	@Column(name="employee_svc_ctr")
	private String employeeSvcCtr;

	@Column(name="employee_svc_region")
	private String employeeSvcRegion;

	@Column(name="flat_rate")
	private String flatRate;

	private String future1;

	private String future2;

	private String future3;

	private String future4;

	private String future5;

	private String future6;

	@Column(name="invoice_num")
	private String invoiceNum;

	@Column(name="line_num")
	private Integer lineNum;

	@Column(name="original_amount")
	private double originalAmount;

	@Column(name="remaining_balance")
	private double remainingBalance;

	private double revenue;

	//bi-directional many-to-one association to InvoiceLineDetail
	@OneToMany(mappedBy="invoiceLineItem")
	private List<InvoiceLineDetail> invoiceLineDetails;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_id")
	private Invoice invoice;

	public InvoiceLineItem() {
	}

	public String getInvoiceLineItemId() {
		return this.invoiceLineItemId;
	}

	public void setInvoiceLineItemId(String invoiceLineItemId) {
		this.invoiceLineItemId = invoiceLineItemId;
	}

	public String getActivityBillable() {
		return this.activityBillable;
	}

	public void setActivityBillable(String activityBillable) {
		this.activityBillable = activityBillable;
	}

	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getAssetNum() {
		return this.assetNum;
	}

	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getEmployeeLct() {
		return this.employeeLct;
	}

	public void setEmployeeLct(String employeeLct) {
		this.employeeLct = employeeLct;
	}

	public String getEmployeeOu() {
		return this.employeeOu;
	}

	public void setEmployeeOu(String employeeOu) {
		this.employeeOu = employeeOu;
	}

	public String getEmployeeSso() {
		return this.employeeSso;
	}

	public void setEmployeeSso(String employeeSso) {
		this.employeeSso = employeeSso;
	}

	public String getEmployeeSvcCtr() {
		return this.employeeSvcCtr;
	}

	public void setEmployeeSvcCtr(String employeeSvcCtr) {
		this.employeeSvcCtr = employeeSvcCtr;
	}

	public String getEmployeeSvcRegion() {
		return this.employeeSvcRegion;
	}

	public void setEmployeeSvcRegion(String employeeSvcRegion) {
		this.employeeSvcRegion = employeeSvcRegion;
	}

	public String getFlatRate() {
		return this.flatRate;
	}

	public void setFlatRate(String flatRate) {
		this.flatRate = flatRate;
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

	public String getInvoiceNum() {
		return this.invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public Integer getLineNum() {
		return this.lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public double getOriginalAmount() {
		return this.originalAmount;
	}

	public void setOriginalAmount(double originalAmount) {
		this.originalAmount = originalAmount;
	}

	public double getRemainingBalance() {
		return this.remainingBalance;
	}

	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public double getRevenue() {
		return this.revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public List<InvoiceLineDetail> getInvoiceLineDetails() {
		return this.invoiceLineDetails;
	}

	public void setInvoiceLineDetails(List<InvoiceLineDetail> invoiceLineDetails) {
		this.invoiceLineDetails = invoiceLineDetails;
	}

	public InvoiceLineDetail addInvoiceLineDetail(InvoiceLineDetail invoiceLineDetail) {
		getInvoiceLineDetails().add(invoiceLineDetail);
		invoiceLineDetail.setInvoiceLineItem(this);

		return invoiceLineDetail;
	}

	public InvoiceLineDetail removeInvoiceLineDetail(InvoiceLineDetail invoiceLineDetail) {
		getInvoiceLineDetails().remove(invoiceLineDetail);
		invoiceLineDetail.setInvoiceLineItem(null);

		return invoiceLineDetail;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}