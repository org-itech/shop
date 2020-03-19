package org.itech.shop.user;

import lombok.Getter;
import lombok.Setter;
import org.itech.shop.cart.Cart;
import org.itech.shop.common.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class User extends AbstractEntity {
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Cart cart;
}
