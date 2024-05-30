package com.jrnoh.springdoc;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@OpenAPIDefinition(
			info = @Info(
					title = "Api persona Titulo"
					,description = "Descripcion ejemplo"
					,termsOfService = "www.juanrenatonoh/terminosservicios"
					,version = "1.0.0"
					,contact = @Contact(
							name = "Juan Renato Noh"
							,url = "https://juanrenatonoh.blogspot.com/"
							,email = "juanrenatonoh@juanrenatonoh.com"
							)
					,license = @License()//se puede agregar una licencia si gusta
					),servers = {
							@Server(description = "DEV" , url = "http://localhost:8080")
							,@Server(description = "QA" , url = "http://qa:8080")
					},security = @SecurityRequirement(name = "Security Person")
		
		)
@SecurityScheme(name = "Security Person"
				, description = "Access token appi "
				,type = SecuritySchemeType.HTTP
				,paramName = HttpHeaders.AUTHORIZATION
				,in = SecuritySchemeIn.HEADER
				,scheme = "bearer",bearerFormat = "JWT")
@Configuration
public class SpringdocConfig {
	
	
	@Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
        	
        	//Aca podriamos agregar la informacion customizada que hacemos por etiquetas de forma programada
        	//openApi.setInfo(null);
        	
            var paths = openApi.getPaths();
            
            var customEndPoint = paths.get("/custom-endpoint");
            
            if(customEndPoint != null) {
            	
            	var operation =  customEndPoint.getGet();
            	operation.setSummary("Summary customizado en codigo");
            	operation.setDescription("Descripción desde codigo");
            	
            	 var apiResponses = new ApiResponses();

                 // Crear una respuesta 200 con un ejemplo
                 var response200 = new ApiResponse()
                         .description("Successful operation")
                         .content(new Content().addMediaType("application/json",
                                 new MediaType()
                                         .schema(new Schema<>().type("object").addProperty("nombre", new Schema<>().type("string")))
                                         .example(new Example().value("{\"name\":\"juan\"}"))));

                 apiResponses.addApiResponse("200", response200);

                 operation.setResponses(apiResponses);
            }
            
        };
    }
	
	
}
