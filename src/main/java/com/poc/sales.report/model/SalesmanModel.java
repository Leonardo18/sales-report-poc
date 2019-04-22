package com.poc.sales.report.model;

import java.math.BigDecimal;
import java.util.List;

public class SalesmanModel {

    private Long id;
    private String taxId;
    private String name;
    private BigDecimal salary;
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

    public List<SaleModel> getSaleModelList() {
        return saleModelList;
    }

    public void setSaleModelList(List<SaleModel> saleModelList) {
        this.saleModelList = saleModelList;
    }
}