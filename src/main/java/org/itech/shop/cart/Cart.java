package org.itech.shop.cart;

import lombok.Getter;
import lombok.Setter;
import org.itech.shop.common.AbstractEntity;
import org.itech.shop.product.Product;
import org.itech.shop.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<CartProduct> products = new ArrayList<>();

    @Transient
    public float getTotalPrice() {
        return Double.valueOf(this.products.stream().mapToDouble(f -> f.getProduct().getPrice()).sum()).floatValue();
    }

    public void addProduct(Product product) {
        CartProduct cartProduct = new CartProduct(new CartProductId(this, product));

        int idx = this.getProducts().indexOf(cartProduct);

        if (idx >= 0) {
            this.getProducts().get(idx).increaseQuantity();
        } else {
            this.products.add(cartProduct);
        }
    }

    public void removeProduct(Product product) {
        CartProduct cartProduct = new CartProduct(new CartProductId(this, product));

        int idx = this.getProducts().indexOf(cartProduct);

        if (idx >= 0) {
            if (this.getProducts().get(idx).decrementQuantity() == 0) {
                this.getProducts().remove(idx);
            }
        }
    }

    public void clearProducts() {
        this.getProducts().clear();
    }
}
