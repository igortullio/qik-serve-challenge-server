package com.igortullio.server.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.igortullio.server.core.domain.PromotionType.QTY_BASED_PRICE_OVERRIDE;

public class PromotionQtyBasedPriceOverride extends Promotion {

    @JsonProperty("required_qty")
    private final Integer requiredQty;
    private final BigDecimal price;

    public PromotionQtyBasedPriceOverride(String id, Integer requiredQty, BigDecimal price) {
        super(id, QTY_BASED_PRICE_OVERRIDE);
        this.requiredQty = requiredQty;
        this.price = price;
    }

    public Integer getRequiredQty() {
        return requiredQty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public BigDecimal calculate(Product product) {
        if (product.quantity() < getRequiredQty()) return BigDecimal.ZERO;

        BigDecimal requiredQty = BigDecimal.valueOf(getRequiredQty());
        BigDecimal newPriceOneItem = getPrice().divide(requiredQty, 2, RoundingMode.HALF_UP);

        BigDecimal quantity = BigDecimal.valueOf(product.quantity());
        BigDecimal totalProductPayable = newPriceOneItem.multiply(quantity);
        return product.totalRaw().subtract(totalProductPayable);
    }

}
