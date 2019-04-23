package com.poc.sales.report.model;

import com.poc.sales.report.builder.CustomerBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CustomerModel {

    private Long id;
    private String taxId;
    private String name;
    private String businessArea;

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

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public static CustomerBuilder getBuilder() { return new CustomerBuilder(); }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
