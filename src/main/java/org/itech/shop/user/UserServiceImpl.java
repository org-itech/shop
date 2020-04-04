package org.itech.shop.user;

import org.itech.shop.cart.Cart;
import org.itech.shop.cart.CartDTO;
import org.itech.shop.cart.CartProduct;
import org.itech.shop.cart.CartProductDTO;
import org.itech.shop.product.Product;
import org.itech.shop.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartDTO getCart(String id) {
        Assert.hasText(id, "user id must be not blank!");

        User user = userRepository.findById(id).orElse(null);

        Assert.notNull(user, "user id not exists!");

        return getCartDTO(user.getCart());
    }

    @Override
    @Transactional
    public void clearCart(String id) {
        Assert.hasText(id, "user id must be not blank!");

        User user = userRepository.findById(id).orElse(null);

        Assert.notNull(user, "user id not exists!");

        user.emptyCart();

        userRepository.save(user);
    }

    @Override
    @Transactional
    public CartDTO addProductToCart(String id, String productId) {
        Assert.hasText(id, "user id must be not blank!");

        User user = userRepository.findById(id).orElse(null);

        Assert.notNull(user, "user id not exists!");

        Assert.hasText(productId, "product id must be not blank!");

        Product product = productRepository.findById(productId).orElse(null);

        Assert.notNull(product, "product id not exists!");

        user.addProductToCart(product);

        userRepository.save(user);

        return getCartDTO(user.getCart());
    }

    @Override
    @Transactional
    public CartDTO removeProductFromCart(String id, String productId) {
        Assert.hasText(id, "user id must be not blank!");

        User user = userRepository.findById(id).orElse(null);

        Assert.notNull(user, "user id not exists!");

        Assert.hasText(productId, "product id must be not blank!");

        Product product = productRepository.findById(productId).orElse(null);

        Assert.notNull(product, "product id not exists!");

        user.removeProductFromCart(product);

        userRepository.save(user);

        return getCartDTO(user.getCart());
    }

    @Override
    @Transactional
    public CartDTO updateCartProducts(String id, List<CartProductDTO> products) {
        Assert.hasText(id, "user id must be not blank!");

        User user = userRepository.findById(id).orElse(null);

        Assert.notNull(user, "user id not exists!");

        if (products == null) {
            user.emptyCart();
        } else {
            Map<Product, Integer> productMap = new HashMap<>(products.size());

            for (CartProductDTO productDTO : products) {
                Product product = productRepository.findById(productDTO.getProductId()).orElse(null);

                Assert.notNull(product, "product id not exists!");

                Assert.isTrue(productDTO.getQuantity() != null && productDTO.getQuantity() > 0, "product quantity should not be negative!");

                if (!productMap.containsKey(product)) {
                    productMap.put(product, 0);
                }

                productMap.replace(product, productMap.get(product) + productDTO.getQuantity());
            }

            user.updateCartProducts(productMap);
        }

        userRepository.save(user);

        return getCartDTO(user.getCart());
    }

    private CartDTO getCartDTO(Cart cart) {
        CartDTO dto = new CartDTO();

        if (cart == null) {
            return dto;
        }

        dto.setTotalPrice(cart.getTotalPrice());
        dto.setProducts(new ArrayList<>());

        for (CartProduct cartProduct : cart.getProducts()) {
            CartProductDTO productDTO = new CartProductDTO();
            productDTO.setCategory(cartProduct.getProduct().getCategory());
            productDTO.setProdName(cartProduct.getProduct().getProdName());
            productDTO.setQuantity(cartProduct.getQuantity());
            productDTO.setPrice(cartProduct.getProduct().getPrice() * cartProduct.getQuantity());

            dto.getProducts().add(productDTO);
        }

        return dto;
    }
}
