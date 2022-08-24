package com.igortullio.server.adapter.web.rest;

import com.igortullio.server.adapter.dto.TotalDto;
import com.igortullio.server.adapter.dto.ProductDto;
import com.igortullio.server.adapter.mapper.ProductMapper;
import com.igortullio.server.adapter.mapper.TotalMapper;
import com.igortullio.server.core.domain.Product;
import com.igortullio.server.core.service.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
@RequestMapping("/checkout")
public record Checkout(CheckoutService checkoutService, ProductMapper productMapper, TotalMapper totalMapper) {

    @PostMapping("/calculate-total")
    @ResponseStatus(HttpStatus.OK)
    public TotalDto calculateTotal(@RequestBody @NotNull Set<ProductDto> productsDto) {
        Set<Product> productsDomain = productMapper.dtoToDomain(productsDto);
        return totalMapper.domainToDto(checkoutService.calculateTotal(productsDomain));
    }

}
