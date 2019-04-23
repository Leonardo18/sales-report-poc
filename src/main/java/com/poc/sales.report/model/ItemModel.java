package com.poc.sales.report.model;

import com.poc.sales.report.builder.ItemBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static ItemBuilder getBuilder() { return new ItemBuilder(); }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
