package com.affiliatedLink.alCore.controller;

import com.affiliatedLink.alCore.dto.ProductRequest;
import com.affiliatedLink.alCore.entity.Product;
import com.affiliatedLink.alCore.exception.ConsumerNotFoundException;
import com.affiliatedLink.alCore.exception.ProductNotFoundException;
import com.affiliatedLink.alCore.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<Product> registerProduct(@RequestBody @Valid ProductRequest productRequest) throws ConsumerNotFoundException {
        Product product = productService.registerProduct(productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
