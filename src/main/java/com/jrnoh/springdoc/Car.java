package com.jrnoh.springdoc;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Schema(description = "Datos del carro") 
@Entity
@Builder
@Data
public class Car {
	
	
	@Schema(description = "Id del carro")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Schema(description = "Modelo del Carro")
	private String model;
}
