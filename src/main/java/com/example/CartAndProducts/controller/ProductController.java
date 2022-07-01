package com.example.CartAndProducts.controller;

import com.example.CartAndProducts.exeption.ProductValidationRequestExeption;
import com.example.CartAndProducts.model.Product;
import com.example.CartAndProducts.service.ProductRequestValidationService;
import com.example.CartAndProducts.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductRequestValidationService productRequestValidationService;

    public ProductController(ProductService productService, ProductRequestValidationService productRequestValidationService) {
        this.productService = productService;
        this.productRequestValidationService = productRequestValidationService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        final List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.FOUND);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Product>> findByName(@PathVariable String name){
        final ResponseEntity<List<Product>> products = productService.findByProductName(name);
        return products;
    }

    @PostMapping()
    public ResponseEntity<List<Product>> addProduct(@RequestBody Product product) throws ProductValidationRequestExeption {
        productRequestValidationService.validateRequest(product);
        return productService.save(product);
    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<Product>> addListOfProducts(@RequestBody List<Product> products){
        return productService.saveAll(products);
    }

    @PutMapping()
    public ResponseEntity<List<Product>> modifyProduct(@RequestBody Product product) throws ProductValidationRequestExeption{
        productRequestValidationService.validateRequest(product);
        productService.modify(product);
        return new ResponseEntity<>(productService.findAll(), HttpStatus.CREATED);
    }

//TODO ne viskas atrodo gerai, kodel grazina pilna lista, nors ir istrina....


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<List<Product>> deleteById(@PathVariable Integer id){
        boolean isDeleted = productService.deleteById(id);
        if (!isDeleted){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }


}
