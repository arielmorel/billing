package com.arielmorel.billing.services;

import com.arielmorel.billing.entities.Product;
import com.arielmorel.billing.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ariel Morel
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public void createProduct(Product product) {
        if(product!=null){
            repository.save(product);
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        if(product!=null && product.getId()!=null){
            repository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
