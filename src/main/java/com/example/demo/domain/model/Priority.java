package com.example.demo.domain.model;

import java.util.Objects;

public record Priority(Integer value) {
    public Priority {
        Objects.requireNonNull(value,"Priority can not be null");
    }
}
