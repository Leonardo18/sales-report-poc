package com.poc.sales.report.builder;

import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;

import java.math.BigDecimal;
import java.util.List;

public class SaleBuilder {

    private SaleModel saleModel = new SaleModel();

    public SaleBuilder() { }

    public SaleBuilder withId(Long id){
        this.saleModel.setId(id);
        return this;
    }

    public SaleBuilder withCode(Long code){
        this.saleModel.setCode(code);
        return this;
    }

    public SaleBuilder withSalesmanName(String salesmanName){
        this.saleModel.setSalesmanName(salesmanName);
        return this;
    }

    public SaleBuilder withTotalSale(BigDecimal totalSale){
        this.saleModel.setTotalSale(totalSale);
        return this;
    }

    public SaleBuilder withItemList(List<ItemModel> itemModelList){
        this.saleModel.setItemModelList(itemModelList);
        return this;
    }

    public SaleModel build(){
        return this.saleModel;
    }
}
