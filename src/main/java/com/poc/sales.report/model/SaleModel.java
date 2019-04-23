package com.poc.sales.report.model;

import com.poc.sales.report.builder.SaleBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

public class SaleModel {

    private Long id;
    private Long code;
    private String salesmanName;
    private BigDecimal totalSale;
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

    public BigDecimal getTotalSale() { return totalSale; }

    public void setTotalSale(BigDecimal totalSale) { this.totalSale = totalSale; }

    public List<ItemModel> getItemModelList() {
        return itemModelList;
    }

    public void setItemModelList(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    public static SaleBuilder getBuilder() { return new SaleBuilder(); }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
