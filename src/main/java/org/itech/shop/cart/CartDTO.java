package org.itech.shop.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private float totalPrice;

    private List<CartProductDTO> products;
}
