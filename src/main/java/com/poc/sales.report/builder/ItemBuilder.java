package com.poc.sales.report.builder;

import com.poc.sales.report.model.ItemModel;

import java.math.BigDecimal;

public class ItemBuilder {

    private ItemModel itemModel = new ItemModel();

    public ItemBuilder() { }

    public ItemBuilder withId(Long id){
        this.itemModel.setId(id);
        return this;
    }

    public ItemBuilder withQuantity(Integer quantity){
        this.itemModel.setQuantity(quantity);
        return this;
    }

    public ItemBuilder withPrice(BigDecimal price){
        this.itemModel.setPrice(price);
        return this;
    }

    public ItemModel build(){
        return this.itemModel;
    }
}
