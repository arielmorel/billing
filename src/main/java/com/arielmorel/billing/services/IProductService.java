package com.arielmorel.billing.services;

import com.arielmorel.billing.entities.Product;

import java.util.List;

public interface IProductService {
    public void createProduct(Product product);
    public boolean updateProduct(Product product);
    public List<Product> getAllProducts();
}
