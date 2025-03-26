package com.example.demo.domain.model;

import java.util.Objects;

public record ProductId(Long value) {
    public ProductId {
        Objects.requireNonNull(value,"Product Id can not be null");
    }
}
