package org.itech.shop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.itech.shop.common.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Product.findByProductId", query = "select p from Product p where p.productId = ?1")
@NamedQuery(name = "Product.findByCategory", query = "select p from Product p where p.category = ?1")
@NamedQuery(name = "Product.findByProdName", query = "select p from Product p where p.prodName = ?1")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product extends AbstractEntity {
    private ProductCategory category;

    @NotNull
    private Integer productId;

    @Size(max = 64)
    @Column(length = 64)
    private String prodName;

    private float price;
}
