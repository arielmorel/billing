package com.arielmorel.billing.repositories;

import com.arielmorel.billing.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ariel Morel
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
