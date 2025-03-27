package com.example.demo.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record DateRange(LocalDateTime start, LocalDateTime end) {
    public DateRange {
        Objects.requireNonNull(start,"Start date can not be null");
        Objects.requireNonNull(start,"End date can not be null");
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }
}
