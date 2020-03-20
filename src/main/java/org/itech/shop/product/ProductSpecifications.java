package org.itech.shop.product;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> category(ProductCategory category) {
        return ((root, query, cb) -> cb.equal(root.get(Product_.CATEGORY), category));
    }

    public static Specification<Product> id(Integer productId) {
        return ((root, query, cb) -> cb.equal(root.get(Product_.PRODUCT_ID), productId));
    }

    public static Specification<Product> name(String prodName) {
        return ((root, query, cb) -> cb.like(root.get(Product_.PROD_NAME), "%" + prodName + "%"));
    }
}
