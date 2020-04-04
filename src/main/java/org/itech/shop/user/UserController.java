package org.itech.shop.user;

import org.itech.shop.cart.CartDTO;
import org.itech.shop.cart.CartProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/cart")
    public CartDTO getCart(@PathVariable String id) {
        return userService.getCart(id);
    }

    @DeleteMapping("/{id}/cart")
    public void emptyCart(@PathVariable String id) {
        userService.clearCart(id);
    }

    @PutMapping("/{id}/cart/{productId}")
    public CartDTO addProductToCart(@PathVariable String id, @PathVariable String productId) {
        return userService.addProductToCart(id, productId);
    }

    @DeleteMapping("/{id}/cart/{productId}")
    public CartDTO removeProductFromCart(@PathVariable String id, @PathVariable String productId) {
        return userService.removeProductFromCart(id, productId);
    }

    @PutMapping("/{id}/cart")
    public CartDTO updateCartProducts(@PathVariable String id, @RequestBody List<CartProductDTO> products){
        return userService.updateCartProducts(id,products);
    }
}
