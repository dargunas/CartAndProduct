package com.example.CartAndProducts.repository;

import com.example.CartAndProducts.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository <Cart, Integer>{

    List<Cart> findByName(String name);
}
