package com.arielmorel.billing.controllers;

import com.arielmorel.billing.entities.Order;
import com.arielmorel.billing.services.IOrderService;
import com.arielmorel.billing.util.DateUtil;
import io.swagger.annotations.Api;
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
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "place a order", response = ResponseEntity.class)
    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity placeOrder(@RequestBody Order body) {
        if(body!=null) {
            System.out.println(body.getComment());
            System.out.println(body.getOrderDetailList().size());
            orderService.placeOrder(body);
            return new ResponseEntity("Order is placed successfully", HttpStatus.CREATED);
        }
    return new ResponseEntity(HttpStatus.OK);
}

    @ApiOperation(value = "View a list of order by params", response = List.class)
    @GetMapping
    public List<Order> getOrderByPeriod(@RequestParam  String startDate, @RequestParam String endDate){
        return orderService.getOrderByPeriod(DateUtil.stringToDate(startDate), DateUtil.stringToDate(endDate));
    }


}
