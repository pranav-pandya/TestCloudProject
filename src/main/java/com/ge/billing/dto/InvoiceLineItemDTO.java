package com.ge.billing.dto;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ge.billing.model.InvoiceLineDetail;

public class InvoiceLineItemDTO {

	private String invoiceLineItemId;
	private String activityBillable;
	private String activityId;
	private String activityType;
	private String assetNum;
	private double cost;
	private String employeeLct;
	private String employeeOu;
	private String employeeSso;
	private String employeeSvcCtr;
	private String employeeSvcRegion;
	private String flatRate;
	private String future1;
	private String future2;
	private String future3;
	private String future4;
	private String future5;
	private String future6;
	private String invoiceNum;
	private Integer lineNum;
	private double originalAmount;
	private double remainingBalance;
	private double revenue;

	private List<InvoiceLineDetailDTO> invoiceLineDetailsDTO;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_id")
	private InvoiceDTO invoiceDTO;

	public InvoiceLineItemDTO() {
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

	public List<InvoiceLineDetailDTO> getInvoiceLineDetailsDTO() {
		return this.invoiceLineDetailsDTO;
	}

	public void setInvoiceLineDetailsDTO(List<InvoiceLineDetailDTO> invoiceLineDetailsDTO) {
		this.invoiceLineDetailsDTO = invoiceLineDetailsDTO;
	}

	public InvoiceLineDetailDTO InvoiceLineDetailDTO(InvoiceLineDetailDTO invoiceLineDetailDTO) {
		getInvoiceLineDetailsDTO().add(invoiceLineDetailDTO);
		invoiceLineDetailDTO.setInvoiceLineItemDTO(this);

		return invoiceLineDetailDTO;
	}

	public InvoiceLineDetail removeInvoiceLineDetail(InvoiceLineDetail invoiceLineDetail) {
		getInvoiceLineDetailsDTO().remove(invoiceLineDetail);
		invoiceLineDetail.setInvoiceLineItem(null);

		return invoiceLineDetail;
	}

	public InvoiceDTO getInvoiceDTO() {
		return this.invoiceDTO;
	}

	public void setInvoiceDTO(InvoiceDTO invoiceDTO) {
		this.invoiceDTO = invoiceDTO;
	}
}
