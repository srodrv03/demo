package com.example.demo.infraestructure.adapter.output.h2;

import com.example.demo.domain.model.BrandId;
import com.example.demo.domain.model.DateRange;
import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.Price;
import com.example.demo.domain.model.PriceId;
import com.example.demo.domain.model.PriceList;
import com.example.demo.domain.model.Priority;
import com.example.demo.domain.model.ProductId;

import java.util.Currency;

public class PriceMapper {

    public Price toDomain(PriceEntity priceEntity) {
        return new Price(new PriceId(priceEntity.getId()), new BrandId(priceEntity.getBrandId()),
                new ProductId(priceEntity.getProductId()),
                new DateRange(priceEntity.getStartDate(), priceEntity.getEndDate()),
                new PriceList(priceEntity.getPriceList()),
                new Money(priceEntity.getPrice(), Currency.getInstance(priceEntity.getCurrency())),
                new Priority(priceEntity.getPriority()));

    }
}
