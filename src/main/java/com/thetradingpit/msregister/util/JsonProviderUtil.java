package com.thetradingpit.msregister.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JsonProviderUtil {
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public <T> String toJson(T document) {
        return objectMapper.writeValueAsString(document);
    }
}
