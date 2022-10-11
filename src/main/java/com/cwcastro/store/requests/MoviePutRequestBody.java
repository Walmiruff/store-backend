package com.cwcastro.store.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MoviePutRequestBody {
    private Long id;
    @Schema(description = "This is the Movie's name", example = "Matrix Revolutions", required = true)
    private String name;
}
