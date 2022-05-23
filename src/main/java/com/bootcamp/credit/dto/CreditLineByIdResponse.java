package com.bootcamp.credit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditLineByIdResponse {
   
	private String idCredit;
	private Double lineCredit;
	private String  creditScore;     // bueno - malo - regular
	private Double  consumeCredit;   // Monto del consumo de credito total
	private Double  availableCredit; // Monto del credito disponible
}
