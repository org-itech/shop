package org.itech.shop.user;

import org.itech.shop.cart.CartDTO;
import org.itech.shop.cart.CartProductDTO;

import java.util.List;

public interface UserService {
    CartDTO getCart(String id);

    void clearCart(String id);

    CartDTO addProductToCart(String id, String productId);

    CartDTO removeProductFromCart(String id, String productId);

    CartDTO updateCartProducts(String id, List<CartProductDTO> products);
}
