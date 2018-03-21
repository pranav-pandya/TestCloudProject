package com.ge.billing.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ge.billing.model.Contract;
@Repository
public interface BillingContractRepository extends JpaRepository<Contract, Integer> {
	
	String strContractListQuery=" select distinct(agreementNum) as agreementNum, "+ 
								" CASE WHEN startDt is null THEN '1900-01-01 00:00:00.0' ELSE startDt END, "+
								" CASE WHEN endDt is null THEN '1900-01-01 00:00:00.0' ELSE endDt END, "+
								" CASE WHEN nxtInvoiceDt is null THEN '1900-01-01 00:00:00.0' ELSE nxtInvoiceDt END, "+
								" invoiceCycleStart, billingFrequency "+
								" ,CASE WHEN poRequired is null THEN '-' ELSE poRequired END, "+
								" CASE WHEN poExpirationDt is null THEN '1900-01-01 00:00:00.0' ELSE poExpirationDt END, "+
								" CASE WHEN eftWithdrawalDt is null THEN '1900-01-01 00:00:00.0' ELSE eftWithdrawalDt END, "+
								" CASE WHEN idnIdNum is null THEN '0' ELSE idnIdNum END, "+
								" agreementName,billingSchedule, agreementType, paymentTerm, "+ 
								" billToAcct,billToAcctNum, billToSiteNum, shipToAcct, shipToSiteNum, poNum, "+
								" eftAch, statusDesc, currency "+
								" from Contract "+
								" where statusDesc in ('Active', 'partial activated', 'hold', 'pre-termination', 'pending termination') "+ 
								//" and endDt>=now()  "+ //As Anil updated on 11/25/2016 We need to remove this.
								" and startDt<=now() "+
								" and (nxtInvoiceDt is null or nxtInvoiceDt<=now())  ";
	
	String strContractLineItemListQuery=" select c from Contract c "+
										" where c.lineItemStatus in ('Active', 'Partial Activated', 'Hold', 'Pre-Termination', 'Pending Termination') "+ 
										" and (c.rcAmount>0 or c.mrcAmount>0) "+
										" and c.agreementNum = ?1 "+
										" and date(c.rcStartDt) < ?2 ";
										
	
	String strUpdateNxtNewInvoiceDtQuery = " update Contract c set c.nxtInvoiceDt = ?1 where c.agreementNum = ?2 ";
	
	String strUpdateRcStartDtQuery = " update Contract c set c.rcStartDt = ?1 where c.agreementNum = ?2 and c.lineItemId = ?3";
	
	@Transactional
	@Query(strContractListQuery)
	List<Contract> getContractList();
	
	@Transactional
	@Query(strContractListQuery+" and agreementNum = ?1")
	List<Contract> getContractListByAgreementNum(String strAgreementNum);
	
	@Transactional
	@Query(strContractLineItemListQuery)
	List<Contract> getContractLineItemList(String agreementNum, Date newNextInvoiceDate);
	
	@Transactional
	@Modifying
	@Query(strUpdateNxtNewInvoiceDtQuery)
	Integer updateNxtNewInvoiceDtByAgreementNumber(java.sql.Date nxtInvoiceDt, String agreementNum);
	
	@Transactional
	@Modifying
	@Query(strUpdateRcStartDtQuery)
	Integer updateRcStartDtByAgreementNumberandLineItemId(Timestamp nxtInvoiceDt, String agreementNum, String strLineItemId);
}
