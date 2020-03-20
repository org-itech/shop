package org.itech.shop.user;

import lombok.Getter;
import lombok.Setter;
import org.itech.shop.cart.Cart;
import org.itech.shop.common.AbstractEntity;
import org.itech.shop.product.Product;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class User extends AbstractEntity {
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Cart cart;

    public Cart addProductToCart(Product product) {
        Cart cart = this.getCart();

        if (cart == null) {
            cart = new Cart(this);
        }

        cart.addProduct(product);

        return cart;
    }

    public Cart removeProductFromCart(Product product) {
        Cart cart = this.getCart();

        if (cart != null) {
            cart.removeProduct(product);
        }

        return cart;
    }

    public Cart emptyCard() {
        Cart cart = this.getCart();

        if (cart != null) {
            cart.clearProducts();
        }

        return cart;
    }
}
