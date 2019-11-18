package com.arielmorel.billing.controllers;

import com.arielmorel.billing.entities.Order;
import com.arielmorel.billing.entities.Product;
import com.arielmorel.billing.repositories.ProductRepository;
import com.arielmorel.billing.services.IOrderService;
import com.arielmorel.billing.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Ariel Morel
 */
@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/create")
    public void createProduct(@RequestBody Product body){
        if(body!=null) {
            productService.createProduct(body);
        }
    }

    @PostMapping(value = "/update")
    public void updateProduct(@RequestBody Product body){
        if(body!=null) {
            productService.updateProduct(body);
        }
    }

    @GetMapping(value = "/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


}
