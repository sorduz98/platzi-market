package com.platzi.market.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Integer productId;
    private String name;
    private Integer categoryId;
    private Double price;
    private Integer stock;
    private Category category;
}
