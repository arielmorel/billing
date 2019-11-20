package com.arielmorel.billing.controllers;

import com.arielmorel.billing.entities.Product;
import com.arielmorel.billing.services.IProductService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ariel Morel
 */
@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @ApiOperation(value = "create a product", response = ResponseEntity.class)
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity createProduct(@RequestBody Product body){
        if(body!=null) {
            productService.createProduct(body);
            return new ResponseEntity("Product is created successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "update a product", response = ResponseEntity.class)
    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity updateProduct(@RequestBody Product body){
        if(productService.updateProduct(body)) {
            return new ResponseEntity("Product is updated successsfully", HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "View a list of Products", response = List.class)
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


}
