
package com.arielmorel.billing.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ariel Morel
 *
 */
@Entity
@Table(name = "order_table")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @Transient
    @OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_detail_id")
    private List<OrderDetail> orderDetailList;

    @Column(name="warranty")
    private int warranty;

    @Column(name = "comment", length = 150)
    private String comment;
    @Column(name="status")
    private int status;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Order() {

        orderDetailList =new ArrayList<>();
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

    public void addItemInvoice(OrderDetail item) {
        this.orderDetailList.add(item);
    }

    public Double getTotal(){
        double total=0D;
        for (OrderDetail detail: orderDetailList) {
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
