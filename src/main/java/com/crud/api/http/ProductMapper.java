package com.crud.api.http;

import com.crud.api.domain.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static Product httpToTable(ProductHttp productHttp) {
        return Product.builder()
                .id(productHttp.getId())
                .description(productHttp.getDescription())
                .quantity(productHttp.getQuantity())
                .value(productHttp.getValue())
                .expire(productHttp.getExpire())
                .build();
    }

    public static ProductHttp tableToHttp(Product product) {
        return ProductHttp.builder()
                .id(product.getId())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .value(product.getValue())
                .expire(product.getExpire())
                .build();
    }
}
