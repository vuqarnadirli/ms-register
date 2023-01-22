package com.thetradingpit.msregister.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClientResponse {
    private UUID clickId;

    public static RegisterClientResponse of(UUID id) {
        return new RegisterClientResponse(id);
    }
}
