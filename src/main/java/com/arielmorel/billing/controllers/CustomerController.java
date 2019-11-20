package com.arielmorel.billing.controllers;

import com.arielmorel.billing.entities.Customer;
import com.arielmorel.billing.repositories.CustomerRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ariel Morel
 */
@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @ApiOperation(value = "create a customer")
    @PostMapping(value = "/create")
    public void create(@RequestBody Customer body){
        if(body==null){
            return;
        }
        customerRepository.save(body);
    }

    @ApiOperation(value = "View a list of customers", response = List.class)
    @GetMapping
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }


}
