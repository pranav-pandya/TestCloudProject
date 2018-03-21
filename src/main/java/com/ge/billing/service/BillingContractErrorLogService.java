package com.ge.billing.service;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ge.billing.dto.ContractErrorLogDTO;
import com.ge.billing.model.ContractErrorLog;
import com.ge.billing.repository.BillingContractErrorLogRepository;


@Service
public class BillingContractErrorLogService {

	@Autowired
	BillingContractErrorLogRepository  billingContractErrorLogRepository;
	private MapperFactory ContractErrorLogMapperFactory = new DefaultMapperFactory.Builder().build();
	private BoundMapperFacade<ContractErrorLog, ContractErrorLogDTO> ContractErrorLogBoundMapper = ContractErrorLogMapperFactory.getMapperFacade(ContractErrorLog.class, ContractErrorLogDTO.class);
	
	@Transactional
	public int  logException(ContractErrorLogDTO contractErrorLogDTO)
	{
		ContractErrorLog contractErrorLog = ContractErrorLogBoundMapper.mapReverse(contractErrorLogDTO);
		return billingContractErrorLogRepository.save(contractErrorLog).getContractErrorLogId();	
	}
	
}
