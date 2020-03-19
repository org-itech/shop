package org.itech.shop.cart;

import lombok.Getter;
import lombok.Setter;
import org.itech.shop.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@IdClass(CartProductId.class)
public class CartProduct {
    @Id
    @ManyToOne
    private Cart cart;

    @Id
    @ManyToOne
    private Product product;

    private Integer quantity;
}
