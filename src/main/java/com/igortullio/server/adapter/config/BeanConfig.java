package com.igortullio.server.adapter.config;

import com.igortullio.server.core.port.ProductFind;
import com.igortullio.server.core.service.CheckoutService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CheckoutService checkoutService(ProductFind productFind) {
        return new CheckoutService(productFind);
    }

}
