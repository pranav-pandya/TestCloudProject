package com.ge.billing.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the contract database table.
 * 
 */
@Entity
public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="agreement_srno")
	private Integer agreementSrno;

	@Column(name="agreement_name")
	private String agreementName;

	@Column(name="agreement_num")
	private String agreementNum;

	@Column(name="agreement_type")
	private String agreementType;

	@Column(name="asset_num")
	private String assetNum;

	@Column(name="bill_to_acct")
	private String billToAcct;

	@Column(name="bill_to_acct_num")
	private Integer billToAcctNum;

	@Column(name="bill_to_site_num")
	private Integer billToSiteNum;

	@Column(name="billing_frequency")
	private String billingFrequency;

	@Column(name="billing_schedule")
	private String billingSchedule;

	@Column(name="charge_end_dt")
	private Timestamp chargeEndDt;

	private String currency;

	@Column(name="eft_ach")
	private String eftAch;

	@Column(name="eft_withdrawal_dt")
	private String eftWithdrawalDt;

	@Column(name="end_dt")
	private Timestamp endDt;

	private String future1;

	private String future2;

	private String future3;

	private String future4;

	private String future5;

	private String future6;

	@Column(name="idn_id_num")
	private Integer idnIdNum;

	@Column(name="invoice_cycle_start")
	private String invoiceCycleStart;

	@Column(name="last_invoice_dt")
	private Timestamp lastInvoiceDt;

	@Column(name="line_end_dt")
	private Timestamp lineEndDt;

	@Column(name="line_item_id")
	private String lineItemId;

	@Column(name="line_item_status")
	private String lineItemStatus;

	@Column(name="line_start_dt")
	private Timestamp lineStartDt;

	@Column(name="mrc_amount")
	private double mrcAmount;

	@Column(name="next_invoice_dt")
	private Timestamp nextInvoiceDt;

	@Column(name="nxt_invoice_dt")
	private Timestamp nxtInvoiceDt;

	@Column(name="payment_term")
	private String paymentTerm;

	@Column(name="po_expiration_dt")
	private Timestamp poExpirationDt;

	@Column(name="po_num")
	private String poNum;

	@Column(name="po_required")
	private String poRequired;

	@Column(name="rc_amount")
	private double rcAmount;

	@Column(name="rc_start_dt")
	private Timestamp rcStartDt;

	@Column(name="ship_to_acct")
	private String shipToAcct;

	@Column(name="ship_to_acct_num")
	private Integer shipToAcctNum;

	@Column(name="ship_to_site_num")
	private Integer shipToSiteNum;

	@Column(name="start_dt")
	private Timestamp startDt;

	@Column(name="status_desc")
	private String statusDesc;

	@Column(name="system_id")
	private String systemId;

	public Contract() {
	}

	public Integer getAgreementSrno() {
		return this.agreementSrno;
	}

	public void setAgreementSrno(Integer agreementSrno) {
		this.agreementSrno = agreementSrno;
	}

	public String getAgreementName() {
		return this.agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
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

	public String getAssetNum() {
		return this.assetNum;
	}

	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}

	public String getBillToAcct() {
		return this.billToAcct;
	}

	public void setBillToAcct(String billToAcct) {
		this.billToAcct = billToAcct;
	}

	public Integer getBillToAcctNum() {
		return this.billToAcctNum;
	}

	public void setBillToAcctNum(Integer billToAcctNum) {
		this.billToAcctNum = billToAcctNum;
	}

	public Integer getBillToSiteNum() {
		return this.billToSiteNum;
	}

	public void setBillToSiteNum(Integer billToSiteNum) {
		this.billToSiteNum = billToSiteNum;
	}

	public String getBillingFrequency() {
		return this.billingFrequency;
	}

	public void setBillingFrequency(String billingFrequency) {
		this.billingFrequency = billingFrequency;
	}

	public String getBillingSchedule() {
		return this.billingSchedule;
	}

	public void setBillingSchedule(String billingSchedule) {
		this.billingSchedule = billingSchedule;
	}

	public Timestamp getChargeEndDt() {
		return this.chargeEndDt;
	}

	public void setChargeEndDt(Timestamp chargeEndDt) {
		this.chargeEndDt = chargeEndDt;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getEftAch() {
		return this.eftAch;
	}

	public void setEftAch(String eftAch) {
		this.eftAch = eftAch;
	}

	public String getEftWithdrawalDt() {
		return this.eftWithdrawalDt;
	}

	public void setEftWithdrawalDt(String eftWithdrawalDt) {
		this.eftWithdrawalDt = eftWithdrawalDt;
	}

	public Timestamp getEndDt() {
		return this.endDt;
	}

	public void setEndDt(Timestamp endDt) {
		this.endDt = endDt;
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

	public Integer getIdnIdNum() {
		return this.idnIdNum;
	}

	public void setIdnIdNum(Integer idnIdNum) {
		this.idnIdNum = idnIdNum;
	}

	public String getInvoiceCycleStart() {
		return this.invoiceCycleStart;
	}

	public void setInvoiceCycleStart(String invoiceCycleStart) {
		this.invoiceCycleStart = invoiceCycleStart;
	}

	public Timestamp getLastInvoiceDt() {
		return this.lastInvoiceDt;
	}

	public void setLastInvoiceDt(Timestamp lastInvoiceDt) {
		this.lastInvoiceDt = lastInvoiceDt;
	}

	public Timestamp getLineEndDt() {
		return this.lineEndDt;
	}

	public void setLineEndDt(Timestamp lineEndDt) {
		this.lineEndDt = lineEndDt;
	}

	public String getLineItemId() {
		return this.lineItemId;
	}

	public void setLineItemId(String lineItemId) {
		this.lineItemId = lineItemId;
	}

	public String getLineItemStatus() {
		return this.lineItemStatus;
	}

	public void setLineItemStatus(String lineItemStatus) {
		this.lineItemStatus = lineItemStatus;
	}

	public Timestamp getLineStartDt() {
		return this.lineStartDt;
	}

	public void setLineStartDt(Timestamp lineStartDt) {
		this.lineStartDt = lineStartDt;
	}

	public double getMrcAmount() {
		return this.mrcAmount;
	}

	public void setMrcAmount(double mrcAmount) {
		this.mrcAmount = mrcAmount;
	}

	public Timestamp getNextInvoiceDt() {
		return this.nextInvoiceDt;
	}

	public void setNextInvoiceDt(Timestamp nextInvoiceDt) {
		this.nextInvoiceDt = nextInvoiceDt;
	}

	public Timestamp getNxtInvoiceDt() {
		return this.nxtInvoiceDt;
	}

	public void setNxtInvoiceDt(Timestamp nxtInvoiceDt) {
		this.nxtInvoiceDt = nxtInvoiceDt;
	}

	public String getPaymentTerm() {
		return this.paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public Timestamp getPoExpirationDt() {
		return this.poExpirationDt;
	}

	public void setPoExpirationDt(Timestamp poExpirationDt) {
		this.poExpirationDt = poExpirationDt;
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

	public double getRcAmount() {
		return this.rcAmount;
	}

	public void setRcAmount(double rcAmount) {
		this.rcAmount = rcAmount;
	}

	public Timestamp getRcStartDt() {
		return this.rcStartDt;
	}

	public void setRcStartDt(Timestamp rcStartDt) {
		this.rcStartDt = rcStartDt;
	}

	public String getShipToAcct() {
		return this.shipToAcct;
	}

	public void setShipToAcct(String shipToAcct) {
		this.shipToAcct = shipToAcct;
	}

	public Integer getShipToAcctNum() {
		return this.shipToAcctNum;
	}

	public void setShipToAcctNum(Integer shipToAcctNum) {
		this.shipToAcctNum = shipToAcctNum;
	}

	public Integer getShipToSiteNum() {
		return this.shipToSiteNum;
	}

	public void setShipToSiteNum(Integer shipToSiteNum) {
		this.shipToSiteNum = shipToSiteNum;
	}

	public Timestamp getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Timestamp startDt) {
		this.startDt = startDt;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

}