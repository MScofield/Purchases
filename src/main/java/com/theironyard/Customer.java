package com.theironyard;

import javax.persistence.*;

/**
 * Created by scofieldservices on 12/26/16.
 */

@Entity
@Table(name = "ECSCustomerDB")
public class Customer {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String customerName;

    @Column(nullable = false)
    String customerEmail;


    public Customer () {
    }

    public Customer(String t) {
        this.customerName = t;
    }

    public Customer(String t, String e) {
        this.customerName = t;
        this.customerEmail = e;
    }

    public Customer(int i, String n, String e) {
        this.id = i;
        this.customerName = n;
        this.customerEmail = e;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
