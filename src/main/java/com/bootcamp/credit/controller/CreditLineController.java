package com.bootcamp.credit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.credit.dto.CreditLineByIdRequest;
import com.bootcamp.credit.dto.CreditLineByIdResponse;
import com.bootcamp.credit.dto.UpdateCreditConsumptionPaymentRequest;
import com.bootcamp.credit.entity.Credit;
import com.bootcamp.credit.service.CreditLineService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/CreditLine")
public class CreditLineController {
	
	private final CreditLineService creditLineService;
	
	@PostMapping("/id")
    public Mono<ResponseEntity<CreditLineByIdResponse>> getCreditLineById(@RequestBody CreditLineByIdRequest creditLineByIdRequest){
        var Credit=this.creditLineService.getCreditLineByIdCredit(creditLineByIdRequest.getIdCredit());
        return Credit.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
	 @PutMapping("/updateCreditConsumptionPayment")
    public Mono<ResponseEntity<Credit>> updateCreditConsumptionPayment(@RequestBody UpdateCreditConsumptionPaymentRequest trx){

        return this.creditLineService.updateCreditConsumptionPayment(trx)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
