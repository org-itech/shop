package org.itech.shop.cart;

import lombok.Getter;
import lombok.Setter;
import org.itech.shop.product.Product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@IdClass(CartProductId.class)
public class CartProduct {
    public CartProduct() {
    }

    public CartProduct(CartProductId id) {
        this.cart = id.getCart();
        this.product = id.getProduct();
    }

    @Id
    @ManyToOne
    private Cart cart;

    @Id
    @ManyToOne
    private Product product;

    private Integer quantity;
}
