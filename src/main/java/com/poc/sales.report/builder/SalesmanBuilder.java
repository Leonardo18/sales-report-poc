package com.poc.sales.report.builder;

import com.poc.sales.report.model.SaleModel;
import com.poc.sales.report.model.SalesmanModel;

import java.math.BigDecimal;
import java.util.List;

public class SalesmanBuilder {

    private SalesmanModel salesmanModel = new SalesmanModel();

    public SalesmanBuilder() { }

    public SalesmanBuilder withId(Long id){
        this.salesmanModel.setId(id);
        return this;
    }

    public SalesmanBuilder withTaxId(String taxId){
        this.salesmanModel.setTaxId(taxId);
        return this;
    }

    public SalesmanBuilder withName(String name){
        this.salesmanModel.setName(name);
        return this;
    }

    public SalesmanBuilder withSalary(BigDecimal salary){
        this.salesmanModel.setSalary(salary);
        return this;
    }

    public SalesmanBuilder withSaleList(List<SaleModel> saleModelList){
        this.salesmanModel.setSaleModelList(saleModelList);
        return this;
    }

    public SalesmanModel build(){
        return this.salesmanModel;
    }
}
