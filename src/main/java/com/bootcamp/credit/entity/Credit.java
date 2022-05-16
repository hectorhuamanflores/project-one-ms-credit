package com.bootcamp.credit.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(value = "PRODUCTS_CREDIT")
public class Credit {

    @Id
    private String  id;              // Identificador Credito
    private Integer documentNumber;  // Dni - Ruc
    private String  tyCredito;       // personal - empresarial
    private String  tyCustomer;      // Persona - Empresarial
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate     dateStar;        // Fecha de creacion
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate     dateEnd;         // Fecha de caducidad
    private String  creditScore;     // bueno - malo - regular
	private Double  lineCredit;      // Linea de credito
	private Double  consumeCredit;   // Monto del consumo de credito total
	private Double  availableCredit; // Monto del credito disponible

}
