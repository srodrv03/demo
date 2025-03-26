package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public record Money(BigDecimal amount, Currency currency) {
    public Money {
        Objects.requireNonNull(amount,"Amount can not be null");
        Objects.requireNonNull(currency,"Currency  can not be null");

        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Amount can not be negative");
        }
    }
}
