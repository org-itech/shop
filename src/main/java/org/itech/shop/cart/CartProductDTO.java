package org.itech.shop.cart;

import lombok.Data;

@Data
public class CartProductDTO {
    private String productId;
    private String prodName;
    private String category;
    private Integer quantity;
    private float price;
}
