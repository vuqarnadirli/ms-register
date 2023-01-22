package com.thetradingpit.msregister.config;

import com.thetradingpit.msregister.client.api.AffiliateClientRegistrationClient;
import com.thetradingpit.msregister.client.api.AffiliateConversionRegistrationClient;
import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {AffiliateConversionRegistrationClient.class, AffiliateClientRegistrationClient.class})
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}