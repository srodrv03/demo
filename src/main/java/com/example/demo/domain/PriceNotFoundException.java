package com.example.demo.domain;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException{

    public PriceNotFoundException(
            Long productId,
            Long brandId,
            LocalDateTime applicationDate
    ) {
        super(createErrorMessage(productId, brandId, applicationDate));
    }

    private static String createErrorMessage(
            Long productId,
            Long brandId,
            LocalDateTime applicationDate
    ) {
        return String.format(
                "Price not found - Product ID: %d, Brand ID: %d, Application Date: %s",
                productId,
                brandId,
                applicationDate
        );
    }
}
