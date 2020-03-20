package org.itech.shop.product;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> list(ProductCategory category, String prodName, Integer productId) {
        Specification<Product> spec = null;

        if (category != null) {
            spec = ProductSpecifications.category(category);
        }

        if (StringUtils.hasText(prodName)) {
            spec = spec == null ? ProductSpecifications.name(prodName) : spec.and(ProductSpecifications.name(prodName));
        }

        if (productId != null) {
            spec = spec == null ? ProductSpecifications.id(productId) : spec.and(ProductSpecifications.id(productId));
        }

        return productRepository.findAll(spec);
    }
}
