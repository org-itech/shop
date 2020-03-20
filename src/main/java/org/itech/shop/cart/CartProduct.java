package org.itech.shop.cart;

import lombok.Getter;
import lombok.Setter;
import org.itech.shop.product.Product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import java.util.Objects;

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
        this.quantity = 1;
    }

    @Id
    @ManyToOne
    private Cart cart;

    @Id
    @ManyToOne
    private Product product;

    private Integer quantity;

    public CartProductId getId() {
        return new CartProductId(this.cart, this.product);
    }

    public int increaseQuantity() {
        this.quantity = (this.quantity == null ? 0 : this.quantity) + 1;

        return this.quantity;
    }

    public int decrementQuantity() {
        this.quantity = (this.quantity == null ? 0 : this.quantity) - 1;

        return this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProduct that = (CartProduct) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
