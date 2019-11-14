package com.arielmorel.billing.repositories;

import com.arielmorel.billing.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Ariel Morel
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
