package com.pradeep.rest_template.service;

import com.pradeep.rest_template.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final RestTemplate restTemplate;
    private String BASE_URI = "https://dummyjson.com/products";

    public Product getProduct(Long productId){
        String url = BASE_URI + String.format("/%d", productId);
        return restTemplate.getForObject(url, Product.class);
    }

    public ResponseEntity<Product> addProduct(Product product) {
        String url = BASE_URI + "/add";
        HttpEntity<Product> entity = new HttpEntity<>(product);
        return restTemplate.postForEntity(url, entity, Product.class);
    }

    public ResponseEntity<Product> updateProduct(Product product) {
        String getProductUrl = BASE_URI + String.format("/%d", product.getId());
        Product existingProduct = restTemplate.getForObject(getProductUrl, Product.class);
        if(existingProduct == null){
            return ResponseEntity.status(404).build();
        }
        String updateProductUrl = BASE_URI + String.format("/%d", product.getId());

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Product> entity = new HttpEntity<>(product, httpHeaders);
        Product updatedProduct = restTemplate.patchForObject(updateProductUrl, entity, Product.class);
        return ResponseEntity.ok(updatedProduct);
    }

    public ResponseEntity<Product> updateProductWithPut(Product product) {
        String getProductUrl = BASE_URI + String.format("/%d", product.getId());
        Product existingProduct = restTemplate.getForObject(getProductUrl, Product.class);
        if(existingProduct == null){
            return ResponseEntity.status(404).build();
        }
        String updateProductUrl = BASE_URI + String.format("/%d", product.getId());

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Product> entity = new HttpEntity<>(product, httpHeaders);
        restTemplate.put(updateProductUrl, entity);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> deleteProduct(Long productId) {
        String getProductUrl = BASE_URI + String.format("/%d", productId);
        Product existingProduct = restTemplate.getForObject(getProductUrl, Product.class);
        if(existingProduct == null){
            return ResponseEntity.status(404).build();
        }

        String deleteProductUrl = BASE_URI + String.format("/%d", productId);
        restTemplate.delete(deleteProductUrl);
        return ResponseEntity.ok(existingProduct);
    }

    public ResponseEntity<Object> headRequest() {
        HttpEntity<Object> entity = new HttpEntity<>(new Object());
        Object result = restTemplate.exchange(BASE_URI, HttpMethod.HEAD, entity, Object.class);
        return ResponseEntity.ok(result);
    }
}
