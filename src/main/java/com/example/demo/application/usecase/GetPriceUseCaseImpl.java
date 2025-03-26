package com.example.demo.application.usecase;

import com.example.demo.application.dto.PriceRequest;
import com.example.demo.application.dto.PriceResponse;
import com.example.demo.domain.PriceNotFoundException;
import com.example.demo.domain.model.Price;
import com.example.demo.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetPriceUseCaseImpl implements GetPriceUseCase {
    private final PriceRepository priceRepository;


    public GetPriceUseCaseImpl(final PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public PriceResponse getPrice(final PriceRequest request) {
        Price price =
                priceRepository.findApplicablePrice(request.productId(), request.brandId(), request.applicationDate())
                        .orElseThrow(() -> new PriceNotFoundException(request.productId(), request.brandId(),
                                request.applicationDate()));

        // Convertir a respuesta
        return PriceResponse.fromDomain(price);
    }
}
