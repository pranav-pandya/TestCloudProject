package com.ge.billing.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asset database table.
 * 
 */
@Entity
public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="asset_srno")
	private Integer assetSrno;

	@Column(name="asset_lct")
	private String assetLct;

	@Column(name="asset_num")
	private String assetNum;

	@Column(name="bill_to_acct_name")
	private String billToAcctName;

	@Column(name="bill_to_acct_num")
	private Integer billToAcctNum;

	@Column(name="bill_to_acct_site_num")
	private Integer billToAcctSiteNum;

	@Column(name="cost_center")
	private Integer costCenter;

	@Column(name="cust_acct_srno")
	private Integer custAcctSrno;

	@Column(name="dept_cost_center")
	private Integer deptCostCenter;

	private String future1;

	private String future2;

	private String future3;

	private String future4;

	private String future5;

	private String future6;

	@Column(name="market_segmet")
	private Integer marketSegmet;

	@Column(name="material_class")
	private String materialClass;

	private Integer modality;

	@Column(name="modality_name")
	private String modalityName;

	@Column(name="operating_unit")
	private String operatingUnit;

	@Column(name="pl_text")
	private String plText;

	private String product;

	@Column(name="service_region")
	private String serviceRegion;

	@Column(name="ship_to_acct_name")
	private String shipToAcctName;

	@Column(name="ship_to_acct_num")
	private Integer shipToAcctNum;

	@Column(name="ship_to_acct_site_num")
	private String shipToAcctSiteNum;

	@Column(name="system_id")
	private String systemId;

	private String zone;

	public Asset() {
	}

	public Integer getAssetSrno() {
		return this.assetSrno;
	}

	public void setAssetSrno(Integer assetSrno) {
		this.assetSrno = assetSrno;
	}

	public String getAssetLct() {
		return this.assetLct;
	}

	public void setAssetLct(String assetLct) {
		this.assetLct = assetLct;
	}

	public String getAssetNum() {
		return this.assetNum;
	}

	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}

	public String getBillToAcctName() {
		return this.billToAcctName;
	}

	public void setBillToAcctName(String billToAcctName) {
		this.billToAcctName = billToAcctName;
	}

	public Integer getBillToAcctNum() {
		return this.billToAcctNum;
	}

	public void setBillToAcctNum(Integer billToAcctNum) {
		this.billToAcctNum = billToAcctNum;
	}

	public Integer getBillToAcctSiteNum() {
		return this.billToAcctSiteNum;
	}

	public void setBillToAcctSiteNum(Integer billToAcctSiteNum) {
		this.billToAcctSiteNum = billToAcctSiteNum;
	}

	public Integer getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(Integer costCenter) {
		this.costCenter = costCenter;
	}

	public Integer getCustAcctSrno() {
		return this.custAcctSrno;
	}

	public void setCustAcctSrno(Integer custAcctSrno) {
		this.custAcctSrno = custAcctSrno;
	}

	public Integer getDeptCostCenter() {
		return this.deptCostCenter;
	}

	public void setDeptCostCenter(Integer deptCostCenter) {
		this.deptCostCenter = deptCostCenter;
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

	public Integer getMarketSegmet() {
		return this.marketSegmet;
	}

	public void setMarketSegmet(Integer marketSegmet) {
		this.marketSegmet = marketSegmet;
	}

	public String getMaterialClass() {
		return this.materialClass;
	}

	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}

	public Integer getModality() {
		return this.modality;
	}

	public void setModality(Integer modality) {
		this.modality = modality;
	}

	public String getModalityName() {
		return this.modalityName;
	}

	public void setModalityName(String modalityName) {
		this.modalityName = modalityName;
	}

	public String getOperatingUnit() {
		return this.operatingUnit;
	}

	public void setOperatingUnit(String operatingUnit) {
		this.operatingUnit = operatingUnit;
	}

	public String getPlText() {
		return this.plText;
	}

	public void setPlText(String plText) {
		this.plText = plText;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getServiceRegion() {
		return this.serviceRegion;
	}

	public void setServiceRegion(String serviceRegion) {
		this.serviceRegion = serviceRegion;
	}

	public String getShipToAcctName() {
		return this.shipToAcctName;
	}

	public void setShipToAcctName(String shipToAcctName) {
		this.shipToAcctName = shipToAcctName;
	}

	public Integer getShipToAcctNum() {
		return this.shipToAcctNum;
	}

	public void setShipToAcctNum(Integer shipToAcctNum) {
		this.shipToAcctNum = shipToAcctNum;
	}

	public String getShipToAcctSiteNum() {
		return this.shipToAcctSiteNum;
	}

	public void setShipToAcctSiteNum(String shipToAcctSiteNum) {
		this.shipToAcctSiteNum = shipToAcctSiteNum;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

}