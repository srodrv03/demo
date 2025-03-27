package com.example.demo.application.usecase;

import com.example.demo.application.dto.PriceRequest;
import com.example.demo.application.dto.PriceResponse;
import com.example.demo.domain.PriceNotFoundException;
import com.example.demo.domain.model.Price;
import com.example.demo.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class GetPriceUseCaseImpl implements GetPriceUseCase {
    private final PriceRepository priceRepository;


    public GetPriceUseCaseImpl(final PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public PriceResponse getPrice(final PriceRequest request) {
        List<Price> applicablePrices = priceRepository.findAllApplicablePrice(request.productId(), request.brandId(),
                request.applicationDate());
        if (applicablePrices.isEmpty()) {
            throw new PriceNotFoundException(request.productId(), request.brandId(), request.applicationDate());
        }

        var selectedPrice = selectAppropiatePrice(applicablePrices).orElseThrow(
                () -> new PriceNotFoundException(request.productId(), request.brandId(), request.applicationDate()));

        return PriceResponse.fromDomain(selectedPrice);
    }

    private Optional<Price> selectAppropiatePrice(final List<Price> applicablePrices) {
        return applicablePrices.stream()
                .max(Comparator.comparing(Price::priority));

    }
}
