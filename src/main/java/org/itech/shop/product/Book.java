package org.itech.shop.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Book extends Product {
    @Builder
    public Book(Integer productId, String prodName, float price, String genre, String author, String publications) {
        super(ProductCategory.BOOK, productId, prodName, price);

        this.genre = genre;
        this.author = author;
        this.publications = publications;
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
