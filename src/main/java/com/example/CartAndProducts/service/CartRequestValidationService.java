package com.example.CartAndProducts.service;

import com.example.CartAndProducts.exeption.CartRequestValidationExeption;
import com.example.CartAndProducts.model.Cart;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartRequestValidationService {

    public static final String NAME = "Name";

    private final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    public void validateRequest(Cart cart) throws CartRequestValidationExeption{
        LOGGER.info("Validating request of cart.......");

        List<String> missingFields = new ArrayList<>();

        if (StringUtils.isEmpty(cart.getName())){
            missingFields.add(NAME);
        }

        if (!missingFields.isEmpty()){
            throw new CartRequestValidationExeption("Request can not be submitted due to missing fields " + missingFields);
        }

        LOGGER.info("Cart request validation was successful");

    }
}
