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
    @NotBlank
    @Column(length = 36)
    private String cartId;

    @Id
    @NotBlank
    @Column(length = 36)
    private String productId;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    private Integer quantity;
}
