package com.ge.billing.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the contract_error_log database table.
 * 
 */
@Entity
@Table(name="contract_error_log")
@NamedQuery(name="ContractErrorLog.findAll", query="SELECT c FROM ContractErrorLog c")
public class ContractErrorLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_error_log_id_seq")
	@SequenceGenerator(name="contract_error_log_id_seq", sequenceName="contract_error_log_id_seq",allocationSize=1)
	@Column(name="contract_error_log_id")
	private Integer contractErrorLogId;

	@Column(name="agreement_num")
	private String agreementNum;

	@Column(name="created_user_date")
	private Timestamp createdUserDate;

	@Column(name="created_user_id")
	private Integer createdUserId;

	@Column(name="error_message")
	private String errorMessage;

	@Column(name="last_updated_user_date")
	private Timestamp lastUpdatedUserDate;

	@Column(name="last_updated_user_id")
	private Integer lastUpdatedUserId;

	@Column(name="line_item_num")
	private String lineItemNum;

	@Column(name="next_invoice_date")
	private Timestamp nextInvoiceDate;

	@Column(name="rc_start_date")
	private Timestamp rcStartDate;

	@Column(name="status_flag")
	private String statusFlag;

	public ContractErrorLog() {
	}

	public Integer getContractErrorLogId() {
		return this.contractErrorLogId;
	}

	public void setContractErrorLogId(Integer contractErrorLogId) {
		this.contractErrorLogId = contractErrorLogId;
	}

	public String getAgreementNum() {
		return this.agreementNum;
	}

	public void setAgreementNum(String agreementNum) {
		this.agreementNum = agreementNum;
	}

	public Timestamp getCreatedUserDate() {
		return this.createdUserDate;
	}

	public void setCreatedUserDate(Timestamp createdUserDate) {
		this.createdUserDate = createdUserDate;
	}

	public Integer getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Timestamp getLastUpdatedUserDate() {
		return this.lastUpdatedUserDate;
	}

	public void setLastUpdatedUserDate(Timestamp lastUpdatedUserDate) {
		this.lastUpdatedUserDate = lastUpdatedUserDate;
	}

	public Integer getLastUpdatedUserId() {
		return this.lastUpdatedUserId;
	}

	public void setLastUpdatedUserId(Integer lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}

	public String getLineItemNum() {
		return this.lineItemNum;
	}

	public void setLineItemNum(String lineItemNum) {
		this.lineItemNum = lineItemNum;
	}

	public Timestamp getNextInvoiceDate() {
		return this.nextInvoiceDate;
	}

	public void setNextInvoiceDate(Timestamp nextInvoiceDate) {
		this.nextInvoiceDate = nextInvoiceDate;
	}

	public Timestamp getRcStartDate() {
		return this.rcStartDate;
	}

	public void setRcStartDate(Timestamp rcStartDate) {
		this.rcStartDate = rcStartDate;
	}

	public String getStatusFlag() {
		return this.statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

}