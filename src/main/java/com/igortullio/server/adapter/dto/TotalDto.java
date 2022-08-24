package com.igortullio.server.adapter.dto;

import java.math.BigDecimal;

public record TotalDto(BigDecimal totalRaw,
                       BigDecimal totalPromos,
                       BigDecimal totalPayable) {
}
