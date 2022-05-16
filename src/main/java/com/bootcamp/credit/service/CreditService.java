package com.bootcamp.credit.service;

import org.springframework.stereotype.Service;

import com.bootcamp.credit.dto.UpdateCreditConsumptionPaymentRequest;
import com.bootcamp.credit.entity.Credit;
import com.bootcamp.credit.repository.CreditRepository;
import com.bootcamp.credit.service.impl.CreditServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditService implements CreditServiceImpl{
	private  final CreditRepository creditRepository;

    @Override
    public Flux<Credit> getAllCredit() {
        return creditRepository.findAll();
    }

    @Override
    public Mono<Credit> getCreditById(String id) {
        return creditRepository.findById(id);
    }
    
    @Override
	public Flux<Credit> getCreditByDocumentNumber(Integer numDoc) {
		log.error("INICIO_CREDIT_DOCUMENT");
		log.info("numDoc: "+numDoc);
		return creditRepository.findByDocumentNumber(numDoc);
	}
    
    @Override
    public Mono<Credit> createCredit(Credit credit) {
    	if(credit !=null) {
    		log.error("INICIO_CREACION_CREDIT");
    		log.info("documentNumber: "+credit.getDocumentNumber());
    		return creditRepository.save(credit);
    	}else {
    		log.error("Credit is null");
    		throw new RuntimeException("Credit is null");
    	}
       
    }

    @Override
    public Mono<Credit> updateCredit(Credit credit) {
    	
        
        return creditRepository.findById(credit.getId())
                .flatMap( object ->{
                	object.setDocumentNumber(credit.getDocumentNumber());
                	object.setTyCredito(credit.getTyCredito());
                	object.setTyCustomer(credit.getTyCustomer());
                	object.setDateStar(credit.getDateStar());
                	object.setDateEnd(credit.getDateEnd());
                	object.setCreditScore(credit.getCreditScore());
                	object.setLineCredit(credit.getLineCredit());
                	object.setConsumeCredit(credit.getConsumeCredit());
                	object.setAvailableCredit(credit.getAvailableCredit());
                    return creditRepository.save(object);
                 });
    }

    @Override
    public Mono<Credit> deleteCredit(String id) {
        return creditRepository.findById(id)
                .flatMap(existscreditRepository -> creditRepository.delete(existscreditRepository)
                        .then(Mono.just(existscreditRepository)));
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
