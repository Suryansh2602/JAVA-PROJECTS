package com.info.springcrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.info.springcrud.entity.Product;
import com.info.springcrud.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product saveProduct(Product p) {
        Product pr = (Product) productRepo.save(p);
        return pr;
    }

    public List<Product> getList() {
        return productRepo.findAll();
    }

    public Product updateProduct(int id, Product p) {
        Product product = productRepo.findById(id).get();

        product.setDiscount(p.getDiscount());
        product.setPrice(p.getPrice());
        product.setTitle(p.getTitle());
        return productRepo.save(product);
    }

    public Product deleteProduct(int id) {

        Product p = productRepo.findById(id).get();

        productRepo.delete(p);
        return p;
    }

}
