package com.company.domain;

import java.util.Objects;
import java.util.UUID;

public class Sale {
    private final String id;
    private final String productType;
    private final int quantity;
    private final double initialValue;
    private double adjustedValue;
    private double totalValue;

    public Sale(String productType, double initialValue) {
        this(productType, initialValue, 1);
    }

    public Sale(String productType, double initialValue, int productQuantity) {
        this.id = UUID.randomUUID().toString();
        this.productType = productType;
        this.initialValue = initialValue;
        this.quantity = productQuantity;
        this.totalValue = initialValue * quantity;
    }

    public Sale adjustPrice(AdjustmentOperation operation, double adjustedPrice) {
        double value = initialValue;
        if (adjustedValue != 0) {
            value = adjustedValue;
        }
        this.adjustedValue = operation.process(value, adjustedPrice);
        this.totalValue = quantity * adjustedValue;
        return this;
    }

    public String getProductType() {
        return productType;
    }

    public double getAdjustedValue() {
        return adjustedValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalValue() {
        return totalValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id.equals(sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
