package com.jrnoh.springdoc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Data
@Schema(description = "Respuesta paginada")
public class PaginatedResponse<T> {
    @Schema(description = "Página actual", example = "1")
    private int page;

    @Schema(description = "Tamaño de página", example = "10")
    private int size;

    @Schema(description = "Total de elementos", example = "100")
    private long totalElements;

    @Schema(description = "Total de páginas", example = "10")
    private int totalPages;

    @Schema(description = "Elementos")
    private List<T> elements;

}
