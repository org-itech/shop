package org.itech.shop;

import org.itech.shop.product.*;
import org.itech.shop.user.User;
import org.itech.shop.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@Transactional
class ShopApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void initData() {
        Book book = Book.builder()
                .prodName("Magic Painting Book")
                .author("Watt, Fiona")
                .productId(9781409)
                .publications("Usborne Publishing Ltd")
                .price(15f)
                .build();

        Apparal apparal = Apparal.builder()
                .prodName("Nike Air Force")
                .productId(9781450)
                .brand("Nike")
                .design("Tinker Hatfield")
                .type("Shoe")
                .price(50f)
                .build();

        productRepository.save(book);

        productRepository.save(apparal);

        User user = new User("manne");

        userRepository.save(user);
    }

    @Test
    void testShoppingCart() {
        // add new user
        Optional<User> opUser = userRepository.findByName("manne");

        Assertions.assertTrue(opUser.isPresent());

        User user = opUser.get();

        Assertions.assertTrue("manne".equals(user.getName()));

        // query product by category
        List<Product> books = productRepository.findByCategory(ProductCategories.BOOK);

        Assertions.assertTrue(books.size() == 1 && books.get(0).getCategory() == ProductCategories.BOOK && books.get(0).getProductId() == 9781409);

        List<Product> apparals = productRepository.findByCategory(ProductCategories.APPARAL);

        Assertions.assertTrue(apparals.size() == 1 && apparals.get(0).getCategory() == ProductCategories.APPARAL && apparals.get(0).getProductId() == 9781450);

        // query product by productId
        Optional<Product> opBook = productRepository.findByProductId(9781409);

        Assertions.assertTrue(opBook.isPresent());

        Product book = opBook.get();

        Assertions.assertTrue(book instanceof Book && book.getProductId() == 9781409);

        // query product by prodName
        Optional<Product> opApparal = productRepository.findByProdName("Nike Air Force");

        Assertions.assertTrue(opApparal.isPresent());

        Product apparal = opApparal.get();

        Assertions.assertTrue(apparal instanceof Apparal && apparal.getProductId() == 9781450);

        // add product to cart
        user.addProductToCart(book);

        Assertions.assertTrue(user.getCart().getProducts().size() == 1);

        Assertions.assertTrue(user.getCart().getProducts().get(0).getQuantity() == 1);

        // add same product to cart
        user.addProductToCart(book);

        Assertions.assertTrue(user.getCart().getProducts().size() == 1);

        Assertions.assertTrue(user.getCart().getProducts().get(0).getQuantity() == 2);

        // remove product from cart
        user.addProductToCart(apparal);

        Assertions.assertTrue(user.getCart().getProducts().size() == 2);

        user.removeProductFromCart(apparal);

        Assertions.assertTrue(user.getCart().getProducts().size() == 1);

        // update products quantity
        Map<Product, Integer> productMap = new HashMap<>(2);

        productMap.put(book, 5);
        productMap.put(apparal, 10);

        user.updateCartProducts(productMap);

        // get cart total price
        Assertions.assertTrue(user.getCart().getTotalPrice() == ((15 * 5f) + (10 * 50f)));

        // empty cart
        user.emptyCart();

        Assertions.assertTrue(user.getCart().getProducts().isEmpty());
    }
}
