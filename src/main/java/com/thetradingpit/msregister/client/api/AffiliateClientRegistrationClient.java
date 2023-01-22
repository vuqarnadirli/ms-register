package com.thetradingpit.msregister.client.api;

import com.thetradingpit.msregister.model.dto.request.RegisterClientRequest;
import com.thetradingpit.msregister.client.model.AffiliateClientRegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "register-client", url = "${client.register-client.url}")
public interface AffiliateClientRegistrationClient {

    @PostMapping
    AffiliateClientRegistrationResponse register(@RequestBody RegisterClientRequest request);

}
