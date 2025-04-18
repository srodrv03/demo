package com.example.demo.application.dto;

import com.example.demo.domain.model.Price;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PriceResponse(Long productId, Long brandId, Integer priceList, LocalDateTime startDate, LocalDateTime endDate,
                            BigDecimal price) {
    public static PriceResponse fromDomain(final Price price) {
        return new PriceResponse(
                price.productId().value(),
                price.brandId().value(),
                price.priceList().value(),
                price.dateRange()
                        .start(),
                price.dateRange()
                        .end(),
                price.price().amount()
        );
    }
}
