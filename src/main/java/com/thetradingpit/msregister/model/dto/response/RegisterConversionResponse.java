package com.thetradingpit.msregister.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterConversionResponse {
    private Long conversionId;

    public static RegisterConversionResponse of(Long id) {
        return new RegisterConversionResponse(id);
    }
}
