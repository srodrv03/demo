package com.example.demo.domain.repository;

import com.example.demo.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findAllApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
