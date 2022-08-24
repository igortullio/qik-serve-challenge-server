package com.igortullio.server.core.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Total {

    private BigDecimal totalRaw;
    private BigDecimal totalPromos;
    private BigDecimal totalPayable;

    public Total() {
        this.totalRaw = BigDecimal.ZERO;
        this.totalPromos = BigDecimal.ZERO;
        this.totalPayable = BigDecimal.ZERO;
    }

    public BigDecimal getTotalRaw() {
        return totalRaw;
    }

    public void setTotalRaw(BigDecimal totalRaw) {
        this.totalRaw = totalRaw;
    }

    public BigDecimal getTotalPromos() {
        return totalPromos;
    }

    public void setTotalPromos(BigDecimal totalPromos) {
        this.totalPromos = totalPromos.setScale(0, RoundingMode.DOWN);
    }

    public BigDecimal getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(BigDecimal totalPayable) {
        this.totalPayable = totalPayable.setScale(0, RoundingMode.UP);
    }

}
