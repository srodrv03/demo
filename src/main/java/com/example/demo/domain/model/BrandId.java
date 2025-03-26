package com.example.demo.domain.model;

import java.util.Objects;

public record BrandId(Long value) {
    public BrandId {
        Objects.requireNonNull(value,"Brand Id can not be null");
    }

}
