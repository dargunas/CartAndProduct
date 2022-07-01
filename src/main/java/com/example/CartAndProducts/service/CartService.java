package com.example.CartAndProducts.service;

import com.example.CartAndProducts.model.Cart;
import com.example.CartAndProducts.model.Product;
import com.example.CartAndProducts.repository.CartRepository;
import com.example.CartAndProducts.repository.ProductRepository;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final Logger LOGGER = LoggerFactory.getLogger(CartService.class);


    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public ResponseEntity<List<Cart>> save(final Cart cart) {
        cartRepository.save(cart);
        LOGGER.info("Saving cart " + cart.getName() + " to database");
        return new ResponseEntity<>(cartRepository.findByName(cart.getName()), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Cart>> findByName(final String name) {
        final List<Cart> carts = cartRepository.findByName(name);
        LOGGER.info("Cart was found !!!");
        return new ResponseEntity<>(carts, HttpStatus.FOUND);
    }

    public boolean deleteById (Integer id) {
        List<Cart> carts = cartRepository.findAll();
        boolean isDeleted = carts.removeIf(cart -> cart.getId().equals(id));
        return isDeleted;
    }

    public void modify (Cart cart){
        LOGGER.warn("Modify Cart method is ON");
        List <Cart> carts = cartRepository.findAll();
        for (Cart cartToModify : carts) {
            if(cartToModify.getName().equals(cart.getName())){
                LOGGER.warn("Something's gonna be modified");
                cartToModify.setProducts(cart.getProducts());
                LOGGER.warn("Something was modified");
            }
        }
    }

    public ResponseEntity<Cart> addProduct(Integer cartId, Product product){
        Cart cart = cartRepository.getReferenceById(cartId);
        List <Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);
        cartRepository.save(cart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    public void deleteProductFromCart(Integer cartId, Integer productId){
        Cart cart = findById(cartId);
        List<Product> products = cart.getProducts();
        products.removeIf(product -> product.getId().equals(productId));
    }

    public Cart findById(Integer id){
        return cartRepository.getReferenceById(id);
    }

    public String addName (Integer id, Cart cart){
        List <Cart> carts = cartRepository.findAll();
        for (Cart cart1 : carts) {
            if (cart1.getId().equals(id)){
                cart1.setName(cart.getName());
                cartRepository.save(cart1);
                LOGGER.warn("Name vas added!!!!!!!!!!!");
            }
        }
        return cart.getName();
    }


}
