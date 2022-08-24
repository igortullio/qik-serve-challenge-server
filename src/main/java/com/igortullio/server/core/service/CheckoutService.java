package com.igortullio.server.core.service;

import com.igortullio.server.core.domain.Product;
import com.igortullio.server.core.domain.Total;
import com.igortullio.server.core.port.ProductFind;

import java.math.BigDecimal;
import java.util.Set;

public record CheckoutService(ProductFind productFind) {

    public Total calculateTotal(Set<Product> productsDomain) {
        return productFind.findAll(productsDomain)
                .stream()
                .reduce(new Total(),
                        (subtotal, product) -> {
                            BigDecimal productTotalRaw = product.totalRaw();
                            BigDecimal productTotalPromo = calculatePromo(product);

                            subtotal.setTotalRaw(subtotal.getTotalRaw().add(productTotalRaw));
                            subtotal.setTotalPromos(subtotal.getTotalPromos().add(productTotalPromo));
                            subtotal.setTotalPayable(subtotal.getTotalPayable().add(productTotalRaw.subtract(productTotalPromo)));

                            return subtotal;
                        },
                        (subTotal1, subTotal2) -> {
                            Total total = new Total();

                            total.setTotalRaw(subTotal1.getTotalRaw().add(subTotal2.getTotalRaw()));
                            total.setTotalPromos(subTotal1.getTotalPromos().add(subTotal2.getTotalPromos()));
                            total.setTotalPayable(subTotal1.getTotalPayable().add(subTotal2.getTotalPayable()));

                            return total;
                        }
                );
    }

    private BigDecimal calculatePromo(Product product) {
        if (product.promotions().isEmpty()) return BigDecimal.ZERO;

        return product.promotions().stream()
                .map(promotion -> promotion.calculate(product))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
