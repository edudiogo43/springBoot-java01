package com.api.apiDemo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import com.api.apiDemo.models.ProductModel;
import com.api.apiDemo.repositories.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public ProductModel save(ProductModel productModel){
        return productRepository.save(productModel);
    }

    public List<ProductModel> findAll(){
        return productRepository.findAll();
    }

    public ProductModel findById(UUID id){
        Optional<ProductModel> product0 = productRepository.findById(id);
        return product0.orElse(null);
    }

    public void delete (UUID id){
        var productModel = new ProductModel();
        productModel.setIdProduct(id);
        productRepository.delete(productModel);
    }

    public ProductModel update(UUID id, ProductModel productModel){
        var userFound = findById(id);
        if (userFound != null) {
            return productRepository.save(productModel);
        }else {
            return productModel;
        }
    }

}
