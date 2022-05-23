package com.bootcamp.credit.service;

import com.bootcamp.credit.dto.CreditLineByIdResponse;
import com.bootcamp.credit.dto.UpdateCreditConsumptionPaymentRequest;
import com.bootcamp.credit.entity.Credit;

import reactor.core.publisher.Mono;

public interface CreditLineService {
	
	
	public Mono<CreditLineByIdResponse> getCreditLineByIdCredit(String idCredit);
	public Mono<Credit> updateCreditConsumptionPayment(UpdateCreditConsumptionPaymentRequest trx); // 1:consumo 2:pagos

}
