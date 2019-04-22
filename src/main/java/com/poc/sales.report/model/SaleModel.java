package com.poc.sales.report.model;

import com.poc.sales.report.builder.SaleBuilder;

import java.util.List;

public class SaleModel {

    private Long id;
    private Long code;
    private String salesmanName;
    private List<ItemModel> itemModelList;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getCode() { return code; }

    public void setCode(Long code) { this.code = code; }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public List<ItemModel> getItemModelList() {
        return itemModelList;
    }

    public void setItemModelList(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    public static SaleBuilder builder() { return new SaleBuilder(); }
}
