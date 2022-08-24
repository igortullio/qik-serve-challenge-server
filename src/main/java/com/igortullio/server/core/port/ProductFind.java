package com.igortullio.server.core.port;

import com.igortullio.server.core.domain.Product;

import java.util.Set;

public interface ProductFind {

    Set<Product> findAll(Set<Product> products);

}
