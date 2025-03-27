package com.example.demo.domain.model;

import java.util.Objects;

public record Priority(Integer value) implements Comparable<Priority> {
    public Priority {
        Objects.requireNonNull(value,"Priority can not be null");
    }
    @Override
    public int compareTo(Priority other) {
        return this.value.compareTo(other.value);
    }
}
