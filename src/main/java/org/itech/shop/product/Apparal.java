package org.itech.shop.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue(value = ProductCategories.APPARAL)
public class Apparal extends Product {
    @Builder
    public Apparal(Integer productId, String prodName, float price, String type, String brand, String design) {
        super(ProductCategories.APPARAL, productId, prodName, price);

        this.type = type;
        this.brand = brand;
        this.design = design;
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
