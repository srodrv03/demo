package com.example.demo.infraestructure.adapter.input.rest;

import com.example.demo.application.dto.PriceResponse;
import com.example.demo.application.usecase.GetPriceUseCase;
import com.example.demo.domain.PriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPriceUseCase getPriceUseCase;


    @ParameterizedTest(name = "Missing parameter: {0}")
    @ValueSource(strings = {"productId", "brandId", "applicationDate"})
    void findPriceMissingParameterthorwsBadrequest(String missingParam) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/api/prices/find");

        switch (missingParam) {
            case "productId":
                requestBuilder.param("brandId", "1")
                        .param("applicationDate", LocalDateTime.now()
                                .toString());
                break;
            case "brandId":
                requestBuilder.param("productId", "1")
                        .param("applicationDate", LocalDateTime.now()
                                .toString());
                break;
            case "applicationDate":
                requestBuilder.param("productId", "1")
                        .param("brandId", "1");
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + missingParam);
        }

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }


    @Test
    void findPriceOK() throws Exception {
        // Preparar datos de prueba
        LocalDateTime now = LocalDateTime.now();
        PriceResponse mockResponse = PriceResponse.builder()
                .productId(1L)
                .brandId(1L)
                .priceList(1)
                .startDate(now)
                .endDate(now.plusDays(1))
                .price(10L)
                .build();

        when(getPriceUseCase.getPrice(ArgumentMatchers.any())).thenReturn(mockResponse);

        mockMvc.perform(get("/api/prices/find").param("productId", "1")
                        .param("brandId", "1")
                        .param("applicationDate", now.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(10));
    }


    @Test
    void findPricePriceNotFound() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        when(getPriceUseCase.getPrice(ArgumentMatchers.any())).thenThrow(
                new PriceNotFoundException(1L, 1L, LocalDateTime.now()));

        mockMvc.perform(get("/api/prices/find").param("productId", "1")
                        .param("brandId", "1")
                        .param("applicationDate", now.toString()))
                .andExpect(status().isNotFound());
    }


}
