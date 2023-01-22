package com.thetradingpit.msregister.controller;

import com.thetradingpit.msregister.model.dto.request.RegisterClientRequest;
import com.thetradingpit.msregister.model.dto.request.RegisterConversionRequest;
import com.thetradingpit.msregister.model.dto.response.RegisterClientResponse;
import com.thetradingpit.msregister.model.dto.response.RegisterConversionResponse;
import com.thetradingpit.msregister.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/client")
    public ResponseEntity<RegisterClientResponse> registerClient(@RequestBody RegisterClientRequest request) {
      return ResponseEntity.ok(registerService.registerClient(request));
    }

    @PostMapping("/conversion")
    public ResponseEntity<RegisterConversionResponse> registerClient(@RequestBody RegisterConversionRequest request) {
        return ResponseEntity.ok(registerService.registerConversion(request));
    }

}
