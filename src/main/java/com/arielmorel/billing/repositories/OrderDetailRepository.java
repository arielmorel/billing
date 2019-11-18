package com.arielmorel.billing.repositories;

import com.arielmorel.billing.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ariel Morel
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
