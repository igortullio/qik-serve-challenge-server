package com.igortullio.server.core.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PromotionBuyXGetYFree.class, name = "BUY_X_GET_Y_FREE"),
        @JsonSubTypes.Type(value = PromotionFlatPercent.class, name = "FLAT_PERCENT"),
        @JsonSubTypes.Type(value = PromotionQtyBasedPriceOverride.class, name = "QTY_BASED_PRICE_OVERRIDE")
})
public abstract class Promotion {

    private final String id;
    private final PromotionType type;

    public Promotion(String id, PromotionType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public PromotionType getType() {
        return type;
    }

    public abstract BigDecimal calculate(Product product);

}
