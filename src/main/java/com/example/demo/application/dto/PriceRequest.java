package com.example.demo.application.dto;




import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PriceRequest(
        @NotNull(message = "Product Id can not be null") Long productId,
        @NotNull(message = "Brand Id can not be null") Long brandId,
        @NotNull(message = "Application date can not be null") LocalDateTime applicationDate) {

}
