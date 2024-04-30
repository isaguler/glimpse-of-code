package com.isaguler.restexceptionhandlerdemo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record Request(@NotBlank String vendor, @Min(0) BigDecimal price, @Min(0) @Max(10) Integer amount) {
}
