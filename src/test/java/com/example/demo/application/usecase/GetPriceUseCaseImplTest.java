package com.example.demo.application.usecase;

import com.example.demo.application.dto.PriceRequest;
import com.example.demo.domain.PriceNotFoundException;
import com.example.demo.domain.model.BrandId;
import com.example.demo.domain.model.DateRange;
import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.Price;
import com.example.demo.domain.model.PriceId;
import com.example.demo.domain.model.PriceList;
import com.example.demo.domain.model.Priority;
import com.example.demo.domain.model.ProductId;
import com.example.demo.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetPriceUseCaseImplTest {
    @Mock
    private PriceRepository priceRepository;
    private GetPriceUseCaseImpl getPriceUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getPriceUseCase = new GetPriceUseCaseImpl(priceRepository);

    }

    @Test
    void getPriceOK() {
        Long productId = 1L;
        Long brandId = 1L;
        var request = new PriceRequest(productId, brandId, LocalDateTime.of(2020, 6, 14, 10, 0));
        var price = createMockPrice();
        when(priceRepository.findApplicablePrice(productId, brandId, LocalDateTime.of(2020, 6, 14, 10, 0))).thenReturn(
                Optional.of(price));

        var priceResponse = getPriceUseCase.getPrice(request);
        assertNotNull(priceResponse);
        assertEquals(price.id()
                .value(), priceResponse.productId());
        assertEquals(price.brandId()
                .value(), priceResponse.brandId());

    }

    @Test
    void getPricethorwPriceNotFoundException() {
        Long productId = 1L;
        Long brandId = 1L;
        var request = new PriceRequest(productId, brandId, LocalDateTime.of(2020, 6, 14, 10, 0));

        when(priceRepository.findApplicablePrice(productId, brandId, LocalDateTime.of(2020, 6, 14, 10, 0))).thenReturn(
                Optional.empty());
        assertThrows(PriceNotFoundException.class, () -> getPriceUseCase.getPrice(request));
    }

    private Price createMockPrice() {
        PriceId priceId = new PriceId(1L);
        BrandId brandId = new BrandId(1L);
        ProductId productId = new ProductId(1L);
        DateRange dateRange =
                new DateRange(LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59));
        PriceList priceList = new PriceList(1);
        Money price = new Money(new BigDecimal(35), Currency.getInstance("EUR"));
        Priority priority = new Priority(1);

        return new Price(priceId, brandId, productId, dateRange, priceList, price, priority);
    }
}
