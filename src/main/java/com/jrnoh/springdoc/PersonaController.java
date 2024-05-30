package com.jrnoh.springdoc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/")
public class PersonaController {
	
	protected PersonService personService;
	
	
	
	public PersonaController(PersonService personService) {
		super();
		this.personService = personService;
	}


	

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersonas() {
        return new ResponseEntity<>(personService.getAllPersonas(), HttpStatus.OK);
    }
    
    
    @Operation(summary = "Crea un nuevo usuario")
	@PostMapping
    public ResponseEntity<Person> addPersona( @RequestBody Person persona) {
		personService.addPersona(persona);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }
    
    
    @Operation(summary = "Obtiene una persona por ID", 
            	responses = {
            			@ApiResponse(description = "Usuario encontrado", responseCode = "200"),
            			@ApiResponse(description = "Usuario no encontrado", responseCode = "404")
            	})
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersona(@Parameter(description = "Id de la persona", required = true) @PathVariable Integer id) {
        Optional<Person> person = personService.getPersona(id);
        
        if(person.isEmpty()) {
        	return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(person.get());
        
    }
    
    @GetMapping("/paginated")
    public ResponseEntity<PaginatedResponse<Person>> getPaginated() {
        
    	// Lógica del controlador
    	var p =  Person.builder()
    			.lastName("Perez")
    			.name("Juan")
    			.build();
    	
        var response = new PaginatedResponse<Person>();
        response.setPage(1);
        response.setSize(1);
        response.setTotalElements(1);
        response.setTotalPages(1);
        response.setElements(Arrays.asList(p));

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/paginatedCar")
    public ResponseEntity<PaginatedResponse<Car>> getPaginatedCar() {
        
    	// Lógica del controlador
    	var c =  Car.builder().model("Ferrari")
    			.build();
    	
        var response = new PaginatedResponse<Car>();
        response.setPage(1);
        response.setSize(1);
        response.setTotalElements(1);
        response.setTotalPages(1);
        response.setElements(Arrays.asList(c));

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/paginatedCountry")
    @Operation(summary = "Obtiene una lista paginada de usuarios")
    @ApiResponses(value = {
    		@ApiResponse(description = "Usuario encontrado", responseCode = "200"  ),
			@ApiResponse(description = "Usuario no encontrado", responseCode = "404")
    })
    public ResponseEntity<PaginatedResponse<Country>> getPaginatedCountry() {
        
    	// Lógica del controlador
    	var c =  Country.builder().name("Mexico")
    			.build();
    	
        var response = new PaginatedResponse<Country>();
        response.setPage(1);
        response.setSize(1);
        response.setTotalElements(1);
        response.setTotalPages(1);
        response.setElements(Arrays.asList(c));
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/custom-endpoint")
    public ResponseEntity<List<Person>> getCustomEndPoint() {
        return new ResponseEntity<>(personService.getAllPersonas(), HttpStatus.OK);
    }
	
}
