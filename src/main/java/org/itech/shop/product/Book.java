package org.itech.shop.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Book extends Product {
    public Book(){
        super(ProductCategory.BOOK);
    }

    @Size(max = 32)
    @Column(length = 32)
    private String genre;

    @Size(max = 64)
    @Column(length = 64)
    private String author;

    @Size(max = 128)
    @Column(length = 128)
    private String publications;
}
