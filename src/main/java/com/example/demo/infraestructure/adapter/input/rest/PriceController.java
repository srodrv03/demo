package com.example.demo.infraestructure.adapter.input.rest;

import com.example.demo.application.dto.PriceRequest;
import com.example.demo.application.dto.PriceResponse;
import com.example.demo.application.usecase.GetPriceUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
    private final GetPriceUseCase getPriceUseCase;

    public PriceController(final GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @Operation(summary = "Find price by product ID, brand ID, and application date")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the price", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PriceResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content)})
    @GetMapping("/find")
    public ResponseEntity<PriceResponse> findPrice(
            @Parameter(description = "ID of the Product") @RequestParam Long productId,
            @Parameter(description = "Id of the Brand") @RequestParam Long brandId,
            @Parameter(description = "Application date") @RequestParam LocalDateTime applicationDate) {
        return ResponseEntity.ok(getPriceUseCase.getPrice(new PriceRequest(productId, brandId, applicationDate)));
    }
}
