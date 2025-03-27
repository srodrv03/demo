package com.example.demo.application.usecase;

import com.example.demo.application.dto.PriceRequest;
import com.example.demo.application.dto.PriceResponse;

public interface GetPriceUseCase {
    PriceResponse getPrice(PriceRequest request);
}
