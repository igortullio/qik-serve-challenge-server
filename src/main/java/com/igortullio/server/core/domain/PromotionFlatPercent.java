package com.igortullio.server.core.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.igortullio.server.core.domain.PromotionType.FLAT_PERCENT;

public class PromotionFlatPercent extends Promotion {

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private final Integer amount;

    public PromotionFlatPercent(String id, Integer amount) {
        super(id, FLAT_PERCENT);
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public BigDecimal calculate(Product product) {
        BigDecimal amount = BigDecimal.valueOf(getAmount());
        return percentage(product.price(), amount);
    }

    public static BigDecimal percentage(BigDecimal base, BigDecimal percentage) {
        return base.multiply(percentage).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
    }

}
