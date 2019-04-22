package com.poc.sales.report.builder;

import com.poc.sales.report.model.CustomerModel;

public class CustomerBuilder {

    private CustomerModel customerModel = new CustomerModel();

    public CustomerBuilder() { }

    public CustomerBuilder withId(Long id){
        this.customerModel.setId(id);
        return this;
    }

    public CustomerBuilder withTaxId(String taxId){
        this.customerModel.setTaxId(taxId);
        return this;
    }

    public CustomerBuilder withName(String name){
        this.customerModel.setName(name);
        return this;
    }

    public CustomerBuilder withBusinessArea(String businessArea){
        this.customerModel.setBusinessArea(businessArea);
        return this;
    }

    public CustomerModel build(){
        return this.customerModel;
    }
}
