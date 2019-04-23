package com.poc.sales.report.service;

import com.poc.sales.report.factory.SaleFactory;
import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;

@Service
public class SaleService {

    private SaleFactory saleFactory;
    private ItemService itemService;
    private static final String GROUP_NAME_ITEM = "itemSale";

    public SaleService(SaleFactory saleFactory, ItemService itemService) {
        this.saleFactory = saleFactory;
        this.itemService = itemService;
    }

    public SaleModel buildSaleByMatcher(Matcher matcher) {
        SaleModel saleModel = saleFactory.create(
                matcher,
                itemService.buildItemListBySale(matcher.group(GROUP_NAME_ITEM))
        );
        saleModel = getTotalSaleFromItems(saleModel);

        return saleModel;
    }

    public SaleModel getSaleMoreExpensive(List<SaleModel> saleModelList) {
        return saleModelList
                .stream()
                .max(Comparator.comparing(SaleModel::getTotalSale))
                .orElseThrow(NoSuchElementException::new);
    }

    private SaleModel getTotalSaleFromItems(SaleModel saleModel) {
        BigDecimal totalItemsValue = saleModel.getItemModelList()
                .stream()
                .map(itemSale -> itemSale.getPrice().multiply(BigDecimal.valueOf(itemSale.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        saleModel.setTotalSale(totalItemsValue);
        return saleModel;
    }
}