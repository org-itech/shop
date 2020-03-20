package org.itech.shop.cart;

import lombok.Getter;
import lombok.Setter;
import org.itech.shop.common.AbstractEntity;
import org.itech.shop.product.Product;
import org.itech.shop.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Cart extends AbstractEntity {
    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE)
    private Set<CartProduct> products = new HashSet<>();

    @Transient
    public float getTotalPrice() {
        return Double.valueOf(this.products.stream().mapToDouble(f -> f.getProduct().getPrice()).sum()).floatValue();
    }

    public void addProduct(Product product) {
        CartProduct cartProduct = new CartProduct(this, product);

        if (this.getProducts().contains(cartProduct)) {

        }
    }

    public void removeProduct(Product product) {

    }
}
