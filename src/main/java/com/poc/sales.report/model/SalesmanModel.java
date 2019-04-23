package com.poc.sales.report.model;

import com.poc.sales.report.builder.SalesmanBuilder;

import java.math.BigDecimal;
import java.util.List;

public class SalesmanModel {

    private Long id;
    private String taxId;
    private String name;
    private BigDecimal salary;
    private BigDecimal totalSalesValue;
    private List<SaleModel> saleModelList;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() { return salary; }

    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public BigDecimal getTotalSalesValue() { return totalSalesValue; }

    public void setTotalSalesValue(BigDecimal totalSalesValue) { this.totalSalesValue = totalSalesValue; }

    public List<SaleModel> getSaleModelList() { return saleModelList; }

    public void setSaleModelList(List<SaleModel> saleModelList) { this.saleModelList = saleModelList; }

    public static SalesmanBuilder builder() { return new SalesmanBuilder(); }
}