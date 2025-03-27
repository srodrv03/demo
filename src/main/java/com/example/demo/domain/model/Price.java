package com.example.demo.domain.model;

import java.util.Objects;

public record Price(PriceId id,
                    BrandId brandId, ProductId productId, DateRange dateRange, PriceList priceList, Money price, Priority priority) {
    public Price{
        Objects.requireNonNull(id,"Price Id can not be null");
        Objects.requireNonNull(brandId,"Brand Id can not be null");
        Objects.requireNonNull(productId,"Product Id can not be null");
        Objects.requireNonNull(dateRange,"Date Range can not be null");
        Objects.requireNonNull(priceList,"Price List can not be null");
        Objects.requireNonNull(price,"Price can not be null");
        Objects.requireNonNull(priority,"Priority can not be null");
    }



}
