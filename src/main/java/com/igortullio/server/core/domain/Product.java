package com.igortullio.server.core.domain;

import java.math.BigDecimal;
import java.util.Set;

public record Product(String id, String name, BigDecimal price, Integer quantity, Set<Promotion> promotions) {

    public BigDecimal totalRaw() {
        BigDecimal quantity = BigDecimal.valueOf(quantity());
        return price().multiply(quantity);
    }

    public Product newVersion(Integer quantity) {
        return new Product(id, name, price, quantity, promotions);
    }

}
