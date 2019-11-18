//package com.arielmorel.billing.controllers;
//
//import com.arielmorel.billing.entities.Order;
//import com.arielmorel.billing.services.IOrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @author Ariel Morel
// */
//@RestController("/order")
//public class OrderController {
//
//    @Autowired
//    private IOrderService orderService;
//
//    @PostMapping(value = "/create")
//    public void placeOrder(@RequestBody Order body){
//        if(body!=null) {
//            orderService.placeOrder(body);
//        }
//    }
//
//    @GetMapping(value="/all")
//    public List<Order> getOrderByPeriod(Date startDate, Date endDate){
//        return orderService.getOrderByPeriod(startDate, endDate);
//    }
//
//
//}
