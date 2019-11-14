package com.arielmorel.billing.repositories;

import com.arielmorel.billing.entities.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ariel Morel
 */
@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {

}
