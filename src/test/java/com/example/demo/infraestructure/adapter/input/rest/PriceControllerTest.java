package com.example.demo.infraestructure.adapter.input.rest;

import com.example.demo.application.usecase.GetPriceUseCase;
import com.example.demo.domain.PriceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/schema.sql", "/data.sql"})
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
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
        void findPricePriceNotFound() throws Exception {
            LocalDateTime now = LocalDateTime.now();

            when(getPriceUseCase.getPrice(ArgumentMatchers.any())).thenThrow(
                    new PriceNotFoundException(1L, 1L, LocalDateTime.now()));

            mockMvc.perform(get("/api/prices/find").param("productId", "1")
                            .param("brandId", "1")
                            .param("applicationDate", now.toString()))
                    .andExpect(status().isNotFound());
        }

    @Nested
    @DisplayName("Proposed test cases")
    class ProposedTestCases {

        @Autowired
        private MockMvc mockMvc;

        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Test
        @DisplayName("Test 1: Request at 10:00 on day 14 for product 35455 and brand 1")
        void test1_RequestAt10AMOn14thForProduct35455() throws Exception {
            LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

            mockMvc.perform(get("/api/prices/find").param("productId", "35455")
                            .param("brandId", "1")
                            .param("applicationDate", testDate.format(formatter)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.price").value(35.50))
                    .andExpect(jsonPath("$.priceList").value(1));
        }

        @Test
        @DisplayName("Test 2: Request at 16:00 on day 14 for product 35455 and brand 1")
        void test2_RequestAt4PMOn14thForProduct35455() throws Exception {
            LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

            mockMvc.perform(get("/api/prices/find").param("productId", "35455")
                            .param("brandId", "1")
                            .param("applicationDate", testDate.format(formatter)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.price").value(25.45))
                    .andExpect(jsonPath("$.priceList").value(2));
        }

        @Test
        @DisplayName("Test 3: Request at 21:00 on day 14 for product 35455 and brand 1")
        void test3_RequestAt9PMOn14thForProduct35455() throws Exception {
            LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);

            mockMvc.perform(get("/api/prices/find").param("productId", "35455")
                            .param("brandId", "1")
                            .param("applicationDate", testDate.format(formatter)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.price").value(35.50))
                    .andExpect(jsonPath("$.priceList").value(1));
        }

        @Test
        @DisplayName("Test 4: Request at 10:00 on day 15 for product 35455 and brand 1")
        void test4_RequestAt10AMOn15thForProduct35455() throws Exception {
            LocalDateTime testDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);

            mockMvc.perform(get("/api/prices/find").param("productId", "35455")
                            .param("brandId", "1")
                            .param("applicationDate", testDate.format(formatter)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.price").value(30.50))
                    .andExpect(jsonPath("$.priceList").value(3));
        }

        @Test
        @DisplayName("Test 5: Request at 21:00 on day 16 for product 35455 and brand 1")
        void test5_RequestAt9PMOn16thForProduct35455() throws Exception {
            LocalDateTime testDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);

            mockMvc.perform(get("/api/prices/find").param("productId", "35455")
                            .param("brandId", "1")
                            .param("applicationDate", testDate.format(formatter)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.price").value(38.95))
                    .andExpect(jsonPath("$.priceList").value(4));
        }

    }
}
