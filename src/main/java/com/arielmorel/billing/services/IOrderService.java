package com.arielmorel.billing.services;

import com.arielmorel.billing.entities.Order;

import java.util.Date;
import java.util.List;

public interface IOrderService {
     Order placeOrder(Order order);
     List<Order> getOrderByPeriod(Date startDate, Date endDate);
     List<Order> findAll();



}

