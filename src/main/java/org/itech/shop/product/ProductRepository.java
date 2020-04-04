package org.itech.shop.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    @Query(name = "Product.findByProductId")
    Optional<Product> findOneByProductId(Integer productId);

    @Query(name = "Product.findByProdName")
    Optional<Product> findOneByProdName(String prodName);

    @Query(name = "Product.findByCategory")
    List<Product> findByCategory(String category);

    @Query(name = "Product.findByProductId")
    List<Product> findByProductId(Integer productId);

    @Query(name = "Product.findByProdName")
    List<Product> findByProdName(String prodName);
}
