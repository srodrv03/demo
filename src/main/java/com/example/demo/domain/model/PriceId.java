package com.example.demo.domain.model;

import java.util.Objects;

public record PriceId(Long value) {
    public PriceId{
        Objects.requireNonNull(value,"PriceId can not be null");
    }
}
