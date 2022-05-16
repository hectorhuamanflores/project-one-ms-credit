package com.bootcamp.credit.repository;

import com.bootcamp.credit.entity.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository  extends ReactiveCrudRepository<Credit,String> {
    /*
     * find(loQuetrae)By(loQueBusca)
     * findByNombreContainingOrApellidoContaining(String nombre,String apellido);
     * 
     */
	Flux<Credit> findByDocumentNumber(Integer documentNumber);
	
}
