package org.itech.shop.product;

import java.util.List;

public interface ProductService {
    List<Product> list(String category,Integer productId,String prodName);
}
