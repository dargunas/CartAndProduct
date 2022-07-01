package com.example.CartAndProducts.service;

import com.example.CartAndProducts.model.Product;
import com.example.CartAndProducts.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ResponseEntity<List<Product>> save(final Product product) {
        productRepository.save(product);
        LOGGER.info("Saving product " + product.getProductName() + " to database");
        return new ResponseEntity<>(productRepository.findByProductName(product.getProductName()), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Product>> saveAll (final List<Product> products){
        for (Product product : products) {
            productRepository.save(product);
            LOGGER.warn("Saving products of list!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Product>> findByProductName(final String name) {
        final List<Product> products = productRepository.findByProductName(name);
        LOGGER.info("Product was found !!!");
        return new ResponseEntity<>(products, HttpStatus.FOUND);
    }

    public boolean deleteById(Integer id){
        List<Product> products = productRepository.findAll();
        boolean isDeleted;
        return isDeleted = products.removeIf(product -> product.getId().equals(id));

    }

    public void modify(Product product){
        LOGGER.warn("Running product to modify method");
        List<Product> products = productRepository.findAll();
        for (Product productToModify : products) {
            if(productToModify.getProductName().equals(product.getProductName())){
                LOGGER.warn("Product to modify is : " + productToModify );
                productToModify.setQuantity(product.getQuantity());
                productToModify.setExpirationDate(product.getExpirationDate());
                productToModify.setPrice(product.getPrice());
                LOGGER.warn("Product was changed " + productToModify);
            }
        }
    }
}