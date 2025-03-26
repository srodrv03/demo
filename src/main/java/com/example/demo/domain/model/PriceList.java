package com.example.demo.domain.model;


import java.util.Objects;

public record PriceList(Integer value) {
    public PriceList {
        Objects.requireNonNull(value,"Price List can not be null");
    }
}
