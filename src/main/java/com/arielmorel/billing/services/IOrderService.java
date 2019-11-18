package com.arielmorel.billing.services;

import com.arielmorel.billing.entities.Order;

import java.util.Date;
import java.util.List;

public interface IOrderService {
    public void placeOrder(Order order);
    public List<Order> getOrderByPeriod(Date startDate, Date endDate);

}

