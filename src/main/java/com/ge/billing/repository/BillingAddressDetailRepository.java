package com.ge.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ge.billing.model.AddressDetail;

public interface BillingAddressDetailRepository extends JpaRepository<AddressDetail, Integer> {

	
	@Transactional
	@Query(" select ad from AddressDetail ad  where siteUsgType = ?1 and siteNum = ?2")
	List<AddressDetail> getAddressDetailsForBillToAndShipTo(String siteUsgType, int siteNum);
	
}
