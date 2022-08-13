package com.platzi.market.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseItem {
    private Integer productId;
    private Integer quantity;
    private Product product;
    private Double total;
    private Boolean active;
}
