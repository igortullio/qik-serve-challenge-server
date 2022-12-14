package com.igortullio.server.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

import static com.igortullio.server.core.domain.PromotionType.BUY_X_GET_Y_FREE;

public class PromotionBuyXGetYFree extends Promotion {

    @JsonProperty("required_qty")
    private final Integer requiredQty;
    @JsonProperty("free_qty")
    private final Integer freeQty;

    public PromotionBuyXGetYFree(String id, Integer requiredQty, Integer freeQty) {
        super(id, BUY_X_GET_Y_FREE);
        this.requiredQty = requiredQty;
        this.freeQty = freeQty;
    }

    public Integer getRequiredQty() {
        return requiredQty;
    }

    public Integer getFreeQty() {
        return freeQty;
    }

    @Override
    public BigDecimal calculate(Product product) {
        if (product.quantity() < getRequiredQty()) return BigDecimal.ZERO;

        Integer freeQuantity = calculateFreeItem(product.quantity(), getRequiredQty(), getFreeQty());
        BigDecimal payableQuantity = BigDecimal.valueOf(product.quantity() - freeQuantity);
        BigDecimal totalProductPayable = product.price().multiply(payableQuantity);
        return product.totalRaw().subtract(totalProductPayable);
    }

    public static Integer calculateFreeItem(Integer quantity, Integer requiredQty, Integer freeCount) {
        Integer rate = quantity / requiredQty;
        return rate * freeCount;
    }

}
