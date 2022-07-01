package com.example.CartAndProducts.repository;

import com.example.CartAndProducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductName(String name);

    void deleteById(Integer id);

}
