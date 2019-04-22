package com.poc.sales.report.factory;

import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;

@Component
public class SaleFactory {

    private static final String GROUP_NAME_ID = "idSale";
    private static final String GROUP_NAME_CODE = "codeSale";
    private static final String GROUP_NAME_ITEM = "itemSale";
    private static final String GROUP_NAME_SALESMAN_NAME = "salesmanNameSale";
    private ItemFactory itemFactory;

    public SaleFactory() { }

    public SaleFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public SaleModel create(Matcher matcher, List<ItemModel> itemModelList) {
        return SaleModel.builder()
                .withId(Long.parseLong(matcher.group(GROUP_NAME_ID)))
                .withCode(Long.parseLong(matcher.group(GROUP_NAME_CODE)))
                .withItemList(itemModelList)
                .withSalesmanName(matcher.group(GROUP_NAME_SALESMAN_NAME))
                .build();
    }
}
