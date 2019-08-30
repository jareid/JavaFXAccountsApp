package com.jreid;

import java.sql.Timestamp;

public class Figure {
    private Timestamp date;
    private Double amount;
    private String description;

    public Figure(Timestamp date, Double amount, String description) {
        this.setDate(date);
        this.setAmount(amount);
        this.setDescription(description);
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
