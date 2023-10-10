package com.hum.ecommerceapi.service;

import java.util.List;
import java.util.Optional;

import com.hum.ecommerceapi.dto.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hum.ecommerceapi.entity.Cart;
import com.hum.ecommerceapi.entity.Product;
import com.hum.ecommerceapi.entity.User;
import com.hum.ecommerceapi.repository.CartRepository;
import com.hum.ecommerceapi.repository.ProductRepository;
import com.hum.ecommerceapi.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    public Payload addToCart(Integer userId, Integer productId, Integer quantity) {
        try {
            if(quantity < 1) {
                return Payload.builder()
                        .statusCode(400)
                        .status("Failed")
                        .reason("Quantity must be greater than zero")
                        .build();
            }

            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                return Payload.builder()
                        .statusCode(404)
                        .status("Failed")
                        .reason("User not found")
                        .build();
            }

            Optional<Product> product = productRepository.findById(productId);
            if (product.isEmpty()) {
                return Payload.builder()
                        .statusCode(404)
                        .status("Failed")
                        .reason("Product not found")
                        .build();
            }

            Cart cart = Cart.builder()
                    .user(user.get())
                    .product(product.get())
                    .quantity(quantity)
                    .subTotal(product.get().getPrice() * quantity)
                    .build();
            cartRepository.save(cart);

            return Payload.builder()
                    .statusCode(200)
                    .status("Success")
                    .reason("Product added to the cart")
                    .build();

        } catch (DataIntegrityViolationException e) {
            return Payload.builder()
                    .statusCode(400)
                    .status("Failed")
                    .reason("Product is already in the cart")
                    .build();

        }
    }

    public Payload updateCart(Integer cartId, Integer quantity) {
        try {
            if(quantity < 1) {
                return Payload.builder()
                        .statusCode(400)
                        .status("Failed")
                        .reason("Quantity must be greater than zero")
                        .build();
            }

            Optional<Cart> cart = cartRepository.findById(cartId);
            if (cart.isEmpty()) {
                return Payload.builder()
                        .statusCode(404)
                        .status("Failed")
                        .reason("Cart not found")
                        .build();
            }

            cart.get().setQuantity(quantity);
            cart.get().setSubTotal(cart.get().getProduct().getPrice() * quantity);
            cartRepository.save(cart.get());

            return Payload.builder()
                    .statusCode(200)
                    .status("Success")
                    .reason("Cart updated")
                    .build();

        } catch (Exception e) {
            return Payload.builder()
                    .statusCode(500)
                    .status("Error")
                    .reason(e.toString())
                    .build();
        }
    }

    public Payload deleteFromCart(Integer cartId) {
        try {
            cartRepository.deleteById(cartId);
            return Payload.builder()
                    .statusCode(200)
                    .status("Success")
                    .reason("Product deleted from the cart")
                    .build();

        } catch (Exception e) {
            return Payload.builder()
                    .statusCode(500)
                    .status("Error")
                    .reason(e.toString())
                    .build();
        }
    }

    public Payload getCart(Integer userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                return Payload.builder()
                        .statusCode(404)
                        .status("Failed")
                        .reason("User not found")
                        .build();
            }

            List<Cart> list = cartRepository.findByUser(user.get());
            if (list.isEmpty()) {
                return Payload.builder()
                        .statusCode(404)
                        .status("Success")
                        .reason("No cart item found")
                        .build();
            }

            return Payload.builder()
                    .statusCode(200)
                    .status("Success")
                    .reason("OK")
                    .data(list)
                    .build();

        } catch (Exception e) {
            return Payload.builder()
                    .statusCode(500)
                    .status("Error")
                    .reason(e.toString())
                    .build();
        }
    }
}
