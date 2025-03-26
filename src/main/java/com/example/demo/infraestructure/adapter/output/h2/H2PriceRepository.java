package com.example.demo.infraestructure.adapter.output.h2;

import com.example.demo.domain.model.Price;
import com.example.demo.domain.repository.PriceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface H2PriceRepository extends PriceRepository, JpaRepository<PriceEntity, Long> {


    @Override
    @Query("""
        SELECT p FROM PriceEntity p 
        WHERE p.productId = :productId 
        AND p.brandId = :brandId 
        AND :applicationDate BETWEEN p.startDate AND p.endDate 
        ORDER BY p.priority DESC 
        LIMIT 1
    """)
    Optional<Price> findApplicablePrice(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("applicationDate") LocalDateTime applicationDate
    );
}
