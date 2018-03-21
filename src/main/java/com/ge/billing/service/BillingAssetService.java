package com.ge.billing.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ge.billing.model.Asset;
import com.ge.billing.repository.BillingAssetRepository;

@Service
public class BillingAssetService {
	
	@Autowired
	BillingAssetRepository billingAssetRepository;
	//private MapperFactory assetMapperFactory = new DefaultMapperFactory.Builder().build();
	//private BoundMapperFacade<Asset, AssetDTO> assetBoundMapper = assetMapperFactory.getMapperFacade(Asset.class, AssetDTO.class);

	public List<Asset> getAssetDetailsByAssetNumber(String strAssetNum){
		List<Asset> assetList = new ArrayList<Asset>();
		try {
			assetList = billingAssetRepository.getAssetDetailsByAssetNumber(strAssetNum);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return assetList;
	}
	
}
