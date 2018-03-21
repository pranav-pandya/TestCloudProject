package com.ge.billing.dto;



import com.ge.billing.dto.CustomerAccountDTO;

public class AssetDTO {
	
	private Integer assetSrno;

	private String assetLct;

	private String assetNum;

	private String billToAcctName;

	private Integer billToAcctNum;

	private Integer billToAcctSiteNum;

	private Integer costCenter;

	private Integer deptCostCenter;

	private String future1;

	private String future2;

	private String future3;

	private String future4;

	private String future5;

	private String future6;

	private Integer marketSegmet;

	private String materialClass;

	private Integer modality;

	private String modalityName;

	private String operatingUnit;

	private String plText;

	private String product;

	private String serviceRegion;

	private String shipToAcctName;

	private Integer shipToAcctNum;

	private String shipToAcctSiteNum;

	private String systemId;

	private String zone;

	//bi-directional many-to-one association to CustomerAccount
	private CustomerAccountDTO customerAccount;

	public AssetDTO() {
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

	public CustomerAccountDTO getCustomerAccount() {
		return this.customerAccount;
	}

	public void setCustomerAccount(CustomerAccountDTO customerAccount) {
		this.customerAccount = customerAccount;
	}

}
