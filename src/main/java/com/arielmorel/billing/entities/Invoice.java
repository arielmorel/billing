/**
 * 
 */
package com.arielmorel.billing.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ariel Morel
 *
 */
@Entity
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Customer customer;

    @OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceDetail> invoiceDetailList;

    private int warranty;
    private String comment;
    private int status;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Invoice() {
        invoiceDetailList=new ArrayList<>();
        this.warranty=30;
    }

    @PrePersist
    private void prePersist(){
        createdAt=new Date();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void addItemInvoice(InvoiceDetail item) {
        this.invoiceDetailList.add(item);
    }

    public Double getTotal(){
        double total=0D;
        for (InvoiceDetail detail: invoiceDetailList) {
            total+=detail.getUnit()*detail.getPrice();
        }
        return total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
