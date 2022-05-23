package com.bootcamp.credit.service.impl;

import org.springframework.stereotype.Service;

import com.bootcamp.credit.dto.CreditLineByIdResponse;
import com.bootcamp.credit.dto.UpdateCreditConsumptionPaymentRequest;
import com.bootcamp.credit.entity.Credit;
import com.bootcamp.credit.repository.CreditRepository;
import com.bootcamp.credit.service.CreditLineService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditLineServiceImpl implements CreditLineService{
    
	private  final CreditRepository creditRepository;
	
	@Override
	public Mono<CreditLineByIdResponse> getCreditLineByIdCredit(String idCredit) {
		CreditLineByIdResponse credLine = new CreditLineByIdResponse();
		return creditRepository.findById(idCredit)
			   .flatMap(objeto -> {
				   credLine.setIdCredit(objeto.getId());
				   credLine.setLineCredit(objeto.getLineCredit());
				   credLine.setCreditScore(objeto.getCreditScore());
				   credLine.setConsumeCredit(objeto.getConsumeCredit());
				   credLine.setAvailableCredit(objeto.getAvailableCredit());
				   return Mono.just(credLine);
			   });
	}
	
	@Override
	public Mono<Credit> updateCreditConsumptionPayment(UpdateCreditConsumptionPaymentRequest trx) {
		log.error("INICIO_CREDIT_DOCUMENT");
		log.info("idCredit: "+trx.getIdCredit());
		log.info("type: "+trx.getType());
		log.info("amount: "+trx.getAmount());
		return creditRepository.findById(trx.getIdCredit())
	                .flatMap( object ->{
						object.setConsumeCredit(object.getConsumeCredit()+trx.getAmount()*trx.getType());
	                	object.setAvailableCredit(object.getAvailableCredit()-trx.getAmount()*trx.getType());
	                    return creditRepository.save(object);
	                });
	}

}
