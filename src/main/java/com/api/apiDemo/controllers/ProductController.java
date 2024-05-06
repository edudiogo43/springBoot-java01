package com.api.apiDemo.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apiDemo.dtos.ProductRecordDTO;
import com.api.apiDemo.models.ProductModel;
import com.api.apiDemo.repositories.ProductRepository;
import com.api.apiDemo.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    // @Autowired
    // ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDTO, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id){
        ProductModel product0 = productService.findById(id);
        return product0 != null ? ResponseEntity.OK(product0) ? ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProductRecordDTO productRecordDTO) {

        ProductModel product0 = productService.findById(id);
        if(product0 == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
            
        BeanUtils.copyProperties(productRecordDTO, product0);
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(product0));
                                                                                
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id) {

        ProductModel product0 = productService.findById(id);
        if(product0 == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
            
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully!");
                                                                                
    }

}
