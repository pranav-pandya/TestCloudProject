package com.ge.billing.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the address_detail database table.
 * 
 */
@Entity
public class AddressDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="address_detail_srno")
	private Integer addressDetailSrno;

	@Column(name="add_type")
	private String addType;

	private String address;

	@Column(name="address_line_2")
	private String addressLine2;

	@Column(name="address_line_3")
	private String addressLine3;

	@Column(name="affiliate_folder")
	private String affiliateFolder;

	private String city;

	private String country;

	@Column(name="cust_acct_num")
	private Integer custAcctNum;

	private String future1;

	private String future2;

	private String future3;

	private String future4;

	private String future5;

	private String future6;

	@Column(name="operating_unit")
	private String operatingUnit;

	@Column(name="oracle_site_id")
	private Integer oracleSiteId;

	private String province;

	@Column(name="site_num")
	private Integer siteNum;

	@Column(name="site_usg_type")
	private String siteUsgType;

	private String state;

	@Column(name="zip_postal_code")
	private String zipPostalCode;

	public AddressDetail() {
	}

	public Integer getAddressDetailSrno() {
		return this.addressDetailSrno;
	}

	public void setAddressDetailSrno(Integer addressDetailSrno) {
		this.addressDetailSrno = addressDetailSrno;
	}

	public String getAddType() {
		return this.addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return this.addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAffiliateFolder() {
		return this.affiliateFolder;
	}

	public void setAffiliateFolder(String affiliateFolder) {
		this.affiliateFolder = affiliateFolder;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getCustAcctNum() {
		return this.custAcctNum;
	}

	public void setCustAcctNum(Integer custAcctNum) {
		this.custAcctNum = custAcctNum;
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

	public String getOperatingUnit() {
		return this.operatingUnit;
	}

	public void setOperatingUnit(String operatingUnit) {
		this.operatingUnit = operatingUnit;
	}

	public Integer getOracleSiteId() {
		return this.oracleSiteId;
	}

	public void setOracleSiteId(Integer oracleSiteId) {
		this.oracleSiteId = oracleSiteId;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getSiteNum() {
		return this.siteNum;
	}

	public void setSiteNum(Integer siteNum) {
		this.siteNum = siteNum;
	}

	public String getSiteUsgType() {
		return this.siteUsgType;
	}

	public void setSiteUsgType(String siteUsgType) {
		this.siteUsgType = siteUsgType;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipPostalCode() {
		return this.zipPostalCode;
	}

	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

}