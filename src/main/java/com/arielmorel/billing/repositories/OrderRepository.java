package com.arielmorel.billing.repositories;

import com.arielmorel.billing.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Ariel Morel
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order>  findByCreatedAtBetween(Date startDate, Date endDate);
}
