package org.itech.shop.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> list(String category,Integer productId,String prodName) {
        if(StringUtils.hasText(category)) {
            return productRepository.findByCategory(category);
        }

        if(productId!=null){
            return productRepository.findByProductId(productId);
        }

        if(StringUtils.hasText(prodName)){
            return productRepository.findByProdName(prodName);
        }

        return productRepository.findAll();
    }
}
