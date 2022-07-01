package com.example.CartAndProducts.controller;

import com.example.CartAndProducts.exeption.CartRequestValidationExeption;
import com.example.CartAndProducts.model.Cart;
import com.example.CartAndProducts.model.Product;
import com.example.CartAndProducts.service.CartRequestValidationService;
import com.example.CartAndProducts.service.CartService;
import com.example.CartAndProducts.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final CartRequestValidationService cartRequestValidationService;

    public CartController(CartService cartService, ProductService productService, CartRequestValidationService cartRequestValidationService) {
        this.cartService = cartService;
        this.productService = productService;
        this.cartRequestValidationService = cartRequestValidationService;
    }

    @GetMapping()
    public ResponseEntity<List<Cart>> getAllCarts(){
        final List<Cart> carts = cartService.findAll();
        return new ResponseEntity<>(carts, HttpStatus.FOUND);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Cart>> findByName(@PathVariable String name){
        final ResponseEntity<List<Cart>> carts = cartService.findByName(name);
        return carts;
    }

    @PostMapping()
    public ResponseEntity<List<Cart>> addCart(@RequestBody Cart cart) throws CartRequestValidationExeption {
        cartRequestValidationService.validateRequest(cart);
        return cartService.save(cart);
    }

    @PutMapping()
    public ResponseEntity<List<Cart>> modify(@RequestBody Cart cart) throws CartRequestValidationExeption{
        cartRequestValidationService.validateRequest(cart);
        cartService.modify(cart);
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.CREATED);
    }

    @PutMapping("/addProduct/{cartId}")
    public ResponseEntity<Cart> addProduct(@PathVariable Integer cartId, @RequestBody @NotNull Product product){
        cartService.addProduct(cartId, product);
        return new ResponseEntity<>(cartService.findById(cartId), HttpStatus.OK);
    }

    @PutMapping("/addName/{id}")
    public ResponseEntity<String> addName(@PathVariable Integer id,@RequestBody Cart cart){
       String name = cartService.addName(id, cart);
        return new ResponseEntity<>(name, HttpStatus.CREATED);
    }

    @PutMapping("/deleteProductFromCart/{cartId_productId}")
    public ResponseEntity<Cart> deleteProductFromCart(@PathVariable Integer cartId, Integer productId){
        cartService.deleteProductFromCart(cartId, productId);
        return new ResponseEntity<>(cartService.findById(cartId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<List<Cart>> deleteById(@PathVariable Integer id){
        boolean isDeleted = cartService.deleteById(id);
        if(!isDeleted){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
    }


}
