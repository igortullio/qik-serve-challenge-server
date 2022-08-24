package com.igortullio.server.adapter.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ProductDto(@NotBlank String id, @NotNull @Min(1) Integer quantity) {
}
