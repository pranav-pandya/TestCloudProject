package com.ge.billing.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customer_account database table.
 * 
 */
@Entity
public class CustomerAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cust_acct_srno")
	private Integer custAcctSrno;

	@Column(name="affiliate_folder")
	private String affiliateFolder;

	@Column(name="cust_acct_name")
	private String custAcctName;

	@Column(name="cust_acct_num")
	private Integer custAcctNum;

	@Column(name="cust_acct_type")
	private String custAcctType;

	private String future1;

	private String future2;

	private String future3;

	private String future4;

	private String future5;

	private String future6;

	public CustomerAccount() {
	}

	public Integer getCustAcctSrno() {
		return this.custAcctSrno;
	}

	public void setCustAcctSrno(Integer custAcctSrno) {
		this.custAcctSrno = custAcctSrno;
	}

	public String getAffiliateFolder() {
		return this.affiliateFolder;
	}

	public void setAffiliateFolder(String affiliateFolder) {
		this.affiliateFolder = affiliateFolder;
	}

	public String getCustAcctName() {
		return this.custAcctName;
	}

	public void setCustAcctName(String custAcctName) {
		this.custAcctName = custAcctName;
	}

	public Integer getCustAcctNum() {
		return this.custAcctNum;
	}

	public void setCustAcctNum(Integer custAcctNum) {
		this.custAcctNum = custAcctNum;
	}

	public String getCustAcctType() {
		return this.custAcctType;
	}

	public void setCustAcctType(String custAcctType) {
		this.custAcctType = custAcctType;
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

}