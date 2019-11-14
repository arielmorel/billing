package com.arielmorel.billing.repositories;

import com.arielmorel.billing.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ariel Morel
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


}
