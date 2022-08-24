package com.igortullio.server.adapter.adapter;

import com.igortullio.server.adapter.config.CustomProperties;
import com.igortullio.server.core.domain.Product;
import com.igortullio.server.core.port.ProductFind;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductFindAdapter implements ProductFind {

    private final WebClient webClient;

    public ProductFindAdapter(WebClient.Builder webClientBuilder, CustomProperties customProperties) {
        this.webClient = webClientBuilder
                .baseUrl(customProperties.getIntegration().getWiremock().getUrl())
                .build();
    }

    @Override
    public Set<Product> findAll(Set<Product> products) {
        return products.stream()
                .map(product -> webClient.get()
                        .uri("/products/{id}", product.id())
                        .retrieve()
                        .bodyToMono(Product.class)
                        .map(productResponse -> productResponse.newVersion(product.quantity()))
                        .block())
                .collect(Collectors.toSet());
    }

}
