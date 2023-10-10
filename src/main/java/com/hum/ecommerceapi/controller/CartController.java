package com.hum.ecommerceapi.controller;

import com.hum.ecommerceapi.dto.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hum.ecommerceapi.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public Payload addToCart(@RequestParam Integer userId, @RequestParam Integer productId,
                             @RequestParam Integer quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    @PutMapping("/update")
    public Payload updateCart(@RequestParam Integer cartId, @RequestParam Integer quantity) {
        return cartService.updateCart(cartId, quantity);
    }

    @DeleteMapping("/delete")
    public Payload deleteFromCart(@RequestParam Integer cartId) {
        return cartService.deleteFromCart(cartId);
    }

    @GetMapping("/get")
    public Payload getCart(@RequestParam Integer userId) {
        return cartService.getCart(userId);
    }
}
