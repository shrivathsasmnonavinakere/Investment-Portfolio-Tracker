package com.investment.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class InvestmentRequestDTO {

    @NotBlank(message = "Investment name is required")
    private String name;

    @NotBlank(message = "Investment type is required")
    private String type;

    @Positive(message = "Quantity must be greater than zero")
    private double quantity;

    @Positive(message = "Buy price must be greater than zero")
    private double buyPrice;

    @Positive(message = "Current price must be greater than zero")
    private double currentPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
