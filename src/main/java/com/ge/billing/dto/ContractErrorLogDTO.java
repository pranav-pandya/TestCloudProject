package com.ge.billing.dto;

import java.sql.Timestamp;


public class ContractErrorLogDTO {

	private Integer contractErrorLogId;

	private String agreementNum;

	private Timestamp createdUserDate;

	private Integer createdUserId;

	private String errorMessage;

	private Timestamp lastUpdatedUserDate;

	private Integer lastUpdatedUserId;

	private String lineItemNum;

	private Timestamp nextInvoiceDate;

	private Timestamp rcStartDate;

	private String statusFlag;

	public ContractErrorLogDTO() {
	}

	
	
	public ContractErrorLogDTO(Integer contractErrorLogId, String agreementNum,
			Timestamp createdUserDate, Integer createdUserId,
			String errorMessage, Timestamp lastUpdatedUserDate,
			Integer lastUpdatedUserId, String lineItemNum,
			Timestamp nextInvoiceDate, Timestamp rcStartDate, String statusFlag) {
		super();
		this.contractErrorLogId = contractErrorLogId;
		this.agreementNum = agreementNum;
		this.createdUserDate = createdUserDate;
		this.createdUserId = createdUserId;
		this.errorMessage = errorMessage;
		this.lastUpdatedUserDate = lastUpdatedUserDate;
		this.lastUpdatedUserId = lastUpdatedUserId;
		this.lineItemNum = lineItemNum;
		this.nextInvoiceDate = nextInvoiceDate;
		this.rcStartDate = rcStartDate;
		this.statusFlag = statusFlag;
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

	@Override
	public String toString() {
		return "ContractErrorLogDTO [contractErrorLogId=" + contractErrorLogId
				+ ", agreementNum=" + agreementNum + ", createdUserDate="
				+ createdUserDate + ", createdUserId=" + createdUserId
				+ ", errorMessage=" + errorMessage + ", lastUpdatedUserDate="
				+ lastUpdatedUserDate + ", lastUpdatedUserId="
				+ lastUpdatedUserId + ", lineItemNum=" + lineItemNum
				+ ", nextInvoiceDate=" + nextInvoiceDate + ", rcStartDate="
				+ rcStartDate + ", statusFlag=" + statusFlag + "]";
	}

}
