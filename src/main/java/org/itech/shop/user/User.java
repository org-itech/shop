package org.itech.shop.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.itech.shop.cart.Cart;
import org.itech.shop.common.AbstractEntity;
import org.itech.shop.product.Product;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@NamedQuery(name = "User.findByName", query = "select u from User u where u.name = ?1")
public class User extends AbstractEntity {
    public User(String name) {
        this.name = name;
    }

    @Size(max = 32)
    @Column(length = 32)
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    public Cart addProductToCart(Product product) {
        Cart cart = this.getCart();

        if (cart == null) {
            cart = new Cart(this);
            this.setCart(cart);
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
