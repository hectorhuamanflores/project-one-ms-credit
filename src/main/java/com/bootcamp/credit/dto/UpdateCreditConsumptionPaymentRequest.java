package com.bootcamp.credit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCreditConsumptionPaymentRequest {
	
	private String   idCredit; // id del credito a actualizar
	private Integer  type;     // type=1=consumo   type=-1=pago
	private Double   amount;   // monto del consumo o pago
	
}
