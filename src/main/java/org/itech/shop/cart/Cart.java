package org.itech.shop.cart;

import lombok.Getter;
import lombok.Setter;
import org.itech.shop.common.AbstractEntity;
import org.itech.shop.user.User;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart extends AbstractEntity {
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE)
    private List<CartProduct> products;

    @Transient
    public float getTotalPrice() {
        return this.products == null ?
                0f : Double.valueOf(this.products.stream().mapToDouble(f -> f.getProduct().getPrice()).sum()).floatValue();
    }
}
