package com.thetradingpit.msregister.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thetradingpit.msregister.model.TransactionTypeEnum;
import com.thetradingpit.msregister.model.dto.request.RegisterClientRequest;
import com.thetradingpit.msregister.model.dto.request.RegisterConversionRequest;
import com.thetradingpit.msregister.model.dto.response.RegisterClientResponse;
import com.thetradingpit.msregister.model.dto.response.RegisterConversionResponse;
import com.thetradingpit.msregister.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterService registerService;

    @Test
    void testRegisterClient() throws Exception {
        UUID expectedId = UUID.randomUUID();
        RegisterClientRequest request = new RegisterClientRequest(UUID.randomUUID(),
                "referralCode",
                "landingPage",
                "userAgent",
                "ip");
        RegisterClientResponse expectedResponse = RegisterClientResponse.of(expectedId);
        when(registerService.registerClient(request)).thenReturn(expectedResponse);

        String requestJson = new ObjectMapper().writeValueAsString(request);
        String responseJson = new ObjectMapper().writeValueAsString(expectedResponse);

        mockMvc.perform(post("/register/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    void testRegisterConversion() throws Exception {
        Long expectedId = 123L;
        UUID orderId = UUID.randomUUID();
        BigDecimal totalPrice = new BigDecimal("100.00");
        UUID clientId = UUID.randomUUID();
        TransactionTypeEnum transactionType = TransactionTypeEnum.NEW;
        RegisterConversionRequest request = new RegisterConversionRequest(orderId,
                totalPrice,
                clientId,
                transactionType);
        RegisterConversionResponse expectedResponse = RegisterConversionResponse.of(expectedId);
        when(registerService.registerConversion(request)).thenReturn(expectedResponse);

        String requestJson = new ObjectMapper().writeValueAsString(request);
        String responseJson = new ObjectMapper().writeValueAsString(expectedResponse);

        mockMvc.perform(post("/register/conversion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }




}
