package com.igortullio.server.adapter.mapper;

import com.igortullio.server.adapter.dto.ProductDto;
import com.igortullio.server.core.domain.Product;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface ProductMapper {

    Set<Product> dtoToDomain(Set<ProductDto> products);

}
