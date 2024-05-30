package com.jrnoh.springdoc;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Schema(description = "Datos del nuevo usuario") 
@Entity
@Builder
@Data
public class Person {
	
	
	@Schema(description = "Id de la persona")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Schema(description = "Nombre de la persona")
	private String name;
	
	@Schema(description = "Apellido de la persona")
	private String lastName;
	
	//Con hidden ocultamos atributos al swagger
	@Hidden
	private String datoOcultar;
}
