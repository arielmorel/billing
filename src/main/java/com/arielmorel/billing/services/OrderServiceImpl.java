package com.arielmorel.billing.services;

import com.arielmorel.billing.entities.Order;
import com.arielmorel.billing.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Ariel Morel
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public void placeOrder(Order order) {
        if(order!=null) {
            repository.save(order);
        }
    }

    @Override
    public List<Order> getOrderByPeriod(Date startDate, Date endDate) {
        return repository.findAll();
    }
}
