package com.cwcastro.store.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MoviePostRequestBody {
    @NotEmpty(message = "The movie name cannot empty")
    @Schema(description = "This is the Movie's name", example = "Matrix Revolutions", required = true)
    private String name;
}
