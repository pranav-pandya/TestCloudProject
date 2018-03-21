package com.ge.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import org.springframework.transaction.annotation.Transactional;

import com.ge.billing.model.Asset;

@Repository
public interface BillingAssetRepository extends JpaRepository<Asset, Integer> {
	
	@Transactional
	@Query(" select a from Asset a where a.assetNum = ?1 ")
	List<Asset> getAssetDetailsByAssetNumber(String assetNum);
	
}
