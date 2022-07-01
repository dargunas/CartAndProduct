package com.example.CartAndProducts.service;

import com.example.CartAndProducts.exeption.ProductValidationRequestExeption;
import com.example.CartAndProducts.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRequestValidationService {

    private static final String PRODUCT_NAME = "ProductName";
    private static final String EXPIRATION_DATE = "ExpirationDate";
    private static final String QUANTITY = "Quantity";

    private final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public void validateRequest(Product product) throws ProductValidationRequestExeption{

        List<String> missingFields = new ArrayList<>();

        if (StringUtils.isEmpty(product.getProductName())){
            missingFields.add(PRODUCT_NAME);
        }

        if (StringUtils.isEmpty(product.getExpirationDate())){
            missingFields.add(EXPIRATION_DATE);
        }

        if (null == product.getQuantity()){
            missingFields.add(QUANTITY);
        }
        if (!missingFields.isEmpty()){
            throw new ProductValidationRequestExeption("Request can not be submitted due to missing fields " + missingFields);
        }
        LOGGER.info("Product request validation was successful");
    }

}
