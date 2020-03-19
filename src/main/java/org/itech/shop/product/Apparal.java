package org.itech.shop.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Apparal extends Product {
    public Apparal() {
        super(ProductCategory.APPARAL);
    }

    @Size(max = 32)
    @Column(length = 32)
    private String type;

    @Size(max = 64)
    @Column(length = 64)
    private String brand;

    @Size(max = 64)
    @Column(length = 64)
    private String design;
}
