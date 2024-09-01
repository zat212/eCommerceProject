package com.lastProject.Service;

import com.lastProject.Entity.Product;
import com.lastProject.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void save(Product product){
        product.setAddedDate(new Date());
        productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }
}
