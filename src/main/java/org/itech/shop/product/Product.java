package org.itech.shop.product;

import lombok.Getter;
import lombok.Setter;
import org.itech.shop.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product extends AbstractEntity {
    public Product() {
    }

    public Product(ProductCategory category) {
        this.category = category;
    }

    private ProductCategory category;

    @NotNull
    private Integer productId;

    @Column(length = 64)
    private String prodName;

    private float price;
}
