package com.pradeep.rest_template.controller;

import com.pradeep.rest_template.model.Product;
import com.pradeep.rest_template.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Long productId){
        return productService.getProduct(productId);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PatchMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @PutMapping
    public ResponseEntity<Product> updateProductWithPut(@RequestBody Product product){
        return productService.updateProductWithPut(product);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long productId){
        return productService.deleteProduct(productId);
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Object> headRequest(){
        return productService.headRequest();
    }
}
