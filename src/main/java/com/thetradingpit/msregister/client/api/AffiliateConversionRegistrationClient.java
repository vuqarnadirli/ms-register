package com.thetradingpit.msregister.client.api;

import com.thetradingpit.msregister.client.model.AffiliateConversionRegistrationResponse;
import com.thetradingpit.msregister.client.model.ClientConversionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "register-conversion", url = "${client.register-conversion.url}")
public interface AffiliateConversionRegistrationClient {

    @PostMapping
    AffiliateConversionRegistrationResponse register(@RequestBody ClientConversionRequest request);
}
