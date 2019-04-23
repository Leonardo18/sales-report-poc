package com.poc.sales.report.service;

import com.poc.sales.report.factory.SalesmanFactory;
import com.poc.sales.report.model.SaleModel;
import com.poc.sales.report.model.SalesmanModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Service
public class SalesmanService {

    private SalesmanFactory salesmanFactory;

    public SalesmanService(SalesmanFactory salesmanFactory) {
        this.salesmanFactory = salesmanFactory;
    }

    public SalesmanModel buildSalesmanByMatcher(Matcher matcher) {
        return salesmanFactory.create(matcher);
    }

    public List<SalesmanModel> setSalesmanSales(List<SalesmanModel> salesmanModelList,
                                                  List<SaleModel> saleModelList) {

        salesmanModelList.forEach(salesman -> {
                    salesman.setSaleModelList(saleModelList.stream()
                            .filter(sale -> sale.getSalesmanName().equals(salesman.getName()))
                            .collect(Collectors.toList()));
                   salesman.setTotalSalesValue(getSalesmanTotalSales(salesman).getTotalSalesValue());
                });
        return salesmanModelList;
    }

    public Integer getTotalSalesman(List<SalesmanModel> salesmanModelList) {
        return salesmanModelList.size();
    }

    public SalesmanModel getWorstSalesman(List<SalesmanModel> salesmanModelList) {
        return salesmanModelList
                .stream()
                .min(Comparator.comparing(SalesmanModel::getTotalSalesValue))
                .orElseThrow(NoSuchElementException::new);
    }

    private SalesmanModel getSalesmanTotalSales(SalesmanModel salesmanModel) {
        BigDecimal totalSalesmanSaleValue = salesmanModel.getSaleModelList()
                .stream()
                .map(SaleModel::getTotalSale)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        salesmanModel.setTotalSalesValue(totalSalesmanSaleValue);
        return salesmanModel;
    }
}
