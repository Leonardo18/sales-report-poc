package com.poc.sales.report.model;

import com.poc.sales.report.builder.ItemBuilder;

import java.math.BigDecimal;

public class ItemModel {

    private Long id;
    private Integer quantity;
    private BigDecimal price;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static ItemBuilder builder() { return new ItemBuilder(); }
}
