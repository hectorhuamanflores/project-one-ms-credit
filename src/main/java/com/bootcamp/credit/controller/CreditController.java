package com.bootcamp.credit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.credit.dto.CreditByIdRequest;
import com.bootcamp.credit.dto.CreditByNumDocRequest;
import com.bootcamp.credit.dto.UpdateCreditConsumptionPaymentRequest;
import com.bootcamp.credit.entity.Credit;
import com.bootcamp.credit.service.impl.CreditServiceImpl;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Credit")
public class CreditController {

	private final CreditServiceImpl creditServiceimpl;

    @GetMapping
    public Mono<ResponseEntity<Flux<Credit>>>getAllCredit() {
        Flux<Credit> list=this.creditServiceimpl.getAllCredit();
        return  Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list));
    }
    
    @PostMapping("/idCredit")
    public Mono<ResponseEntity<Credit>> getCreditById(@RequestBody CreditByIdRequest creditByIdRequest){
        var Credit=this.creditServiceimpl.getCreditById(creditByIdRequest.getIdCredit());
        return Credit.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/numberDocument")
    public Mono<ResponseEntity<Flux<Credit>>> getCreditByNumDoc(@RequestBody CreditByNumDocRequest CreditByNumDocRequest){
    	Flux<Credit> list=this.creditServiceimpl.getCreditByDocumentNumber(CreditByNumDocRequest.getDocumentNumber());
        return  Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list));
    
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Credit> create(@RequestBody Credit Credit){
        return this.creditServiceimpl.createCredit(Credit);
    }

    @PutMapping("/updateCredit")
    public Mono<ResponseEntity<Credit>> updateCredit(@RequestBody Credit Credit){

        return this.creditServiceimpl.updateCredit(Credit)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCreditById(@PathVariable String id){
        return this.creditServiceimpl.deleteCredit(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/updateCreditConsumptionPayment")
    public Mono<ResponseEntity<Credit>> updateCreditConsumptionPayment(@RequestBody UpdateCreditConsumptionPaymentRequest trx){

        return this.creditServiceimpl.updateCreditConsumptionPayment(trx)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
