package com.bootcamp.credit.service.impl;

import com.bootcamp.credit.dto.UpdateCreditConsumptionPaymentRequest;
import com.bootcamp.credit.entity.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditServiceImpl {

    public Flux<Credit> getAllCredit();
    public Mono<Credit> getCreditById(String id);
    public Mono<Credit> createCredit(Credit credit);
    public Mono<Credit> updateCredit(Credit credit);
    public Mono<Credit> deleteCredit(String id);
    public Flux<Credit> getCreditByDocumentNumber(Integer numdoc);
    
    public Mono<Credit> updateCreditConsumptionPayment(UpdateCreditConsumptionPaymentRequest trx); // 1:consumo 2:pagos


}
