package com.arielmorel.billing.controllers;

import com.arielmorel.billing.entities.Customer;
import com.arielmorel.billing.entities.Order;
import com.arielmorel.billing.services.IOrderService;
import com.arielmorel.billing.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Ariel Morel
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping(value = "/create")
    public void placeOrder(@RequestBody Order body){
            orderService.placeOrder(body);
    }

    @GetMapping
    public List<Order> getOrderByPeriod(@RequestParam  String startDate, @RequestParam String endDate){
        System.out.println(startDate);
        System.out.println(endDate);
        return orderService.getOrderByPeriod(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate));
    }


}
