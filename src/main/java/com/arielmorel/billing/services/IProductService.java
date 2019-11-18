package com.arielmorel.billing.services;

import com.arielmorel.billing.entities.Product;

import java.util.List;

public interface IProductService {
    void createProduct(Product product);
    boolean updateProduct(Product product);
    List<Product> getAllProducts();
}
