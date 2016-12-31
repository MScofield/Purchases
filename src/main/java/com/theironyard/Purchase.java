package com.theironyard;

import javax.persistence.*;

/**
 * Created by scofieldservices on 12/26/16.
 */
@Entity
@Table(name = "ECSpurchasesDB")

public class Purchase {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String cardNumber;

    @Column(nullable = false)
    Integer cvv;

    @Column(nullable = false)
    String category;

    @ManyToOne
    Customer customer;

    public Purchase () {
    }

    public Purchase(int id) {
        this.id = id;
    }

    public Purchase(String d, String n, Integer v, String c, Customer t) {
        this.date = d;
        this.cardNumber = n;
        this.cvv = v;
        this.category = c;
        this.customer = t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
