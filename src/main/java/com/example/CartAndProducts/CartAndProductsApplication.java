package com.example.CartAndProducts;

import com.example.CartAndProducts.model.Cart;
import com.example.CartAndProducts.model.Product;
import com.example.CartAndProducts.repository.CartRepository;
import com.example.CartAndProducts.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CartAndProductsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(CartAndProductsApplication.class, args);
		CartRepository cartRepository = configurableApplicationContext.getBean(CartRepository.class);
		ProductRepository productRepository = configurableApplicationContext.getBean(ProductRepository.class);


//		Cart john = Cart.builder().name("John").build();
//		Cart petter = Cart.builder().name("Petter").build();
//		Cart tony = Cart.builder().name("Tony").build();
//		cartRepository.save(john);
//		cartRepository.save(petter);
//		cartRepository.save(tony);
//
//		Product carrot = Product.builder().productName("carrot").expirationDate("2022-09-10").quantity(15.5).build();
//		Product cucumber = Product.builder().productName("cucumber").expirationDate("2022-07-15").quantity(10.1).build();
//		Product apple = Product.builder().productName("apple").expirationDate("2023-02-30").quantity(5.0).build();
//		productRepository.save(carrot);
//		productRepository.save(cucumber);
//		productRepository.save(apple);
	}

}
