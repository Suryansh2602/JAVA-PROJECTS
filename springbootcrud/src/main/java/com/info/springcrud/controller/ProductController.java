package com.info.springcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.info.springcrud.entity.Product;
import com.info.springcrud.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product save(@RequestBody Product p) {
        return productService.saveProduct(p);
    }

    @GetMapping
    public List<Product> getList() {
        return productService.getList();
    }

  @PatchMapping("/{id}")
public Product updateProduct(@PathVariable int id, @RequestBody Product p) {
    return productService.updateProduct(id, p);
}


    @DeleteMapping("/{id}")
public Product deleteProduct(@PathVariable int id) {
    return productService.deleteProduct(id);
}


}
