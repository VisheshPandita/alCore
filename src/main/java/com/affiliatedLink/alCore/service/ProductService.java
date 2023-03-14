package com.affiliatedLink.alCore.service;

import com.affiliatedLink.alCore.dto.ProductRequest;
import com.affiliatedLink.alCore.entity.Consumer;
import com.affiliatedLink.alCore.entity.Product;
import com.affiliatedLink.alCore.exception.ConsumerNotFoundException;
import com.affiliatedLink.alCore.exception.ProductNotFoundException;
import com.affiliatedLink.alCore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ConsumerService consumerService;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product registerProduct(ProductRequest productRequest) throws ConsumerNotFoundException {
        Consumer consumer = consumerService.getConsumerById(productRequest.getProductOwner());
        Product product = new Product(
                productRequest.getProductName(),
                productRequest.getProductUrl(),
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()),
                consumer
        );

        return productRepository.save(product);
    }

    public Product getProductById(UUID productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) return product.get();
        else throw new ProductNotFoundException("Product not found with id " + productId);
    }
}
