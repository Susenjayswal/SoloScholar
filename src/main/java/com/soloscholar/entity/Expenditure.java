package com.soloscholar.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Expenditure {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Invoice number cannot be blank")
    
    private String invoiceNumber;

    @NotBlank(message = "Type cannot be blank")
    @Size(min=5)
    private String type;

    @NotBlank(message = "Category cannot be blank")
    @Size(min=5)
    private String category;


    @NotNull(message = "Rate cannot be null")
    private double rate;

    
    @NotNull(message = "Quantity cannot be null")
    private int quantity;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Date must be today or before")
    private Date date;

    @NotNull(message = "Total price cannot be null")
    private double totalPrice;
    
    
    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
        calculateTotalPrice(); // update total price when rate changes
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotalPrice(); // update total price when quantity changes
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @PrePersist
    private void calculateTotalPrice() {
        totalPrice = rate * quantity;
    }
}