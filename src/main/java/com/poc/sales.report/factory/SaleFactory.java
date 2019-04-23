package com.poc.sales.report.factory;

import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;

@Component
public class SaleFactory {

    private static final String GROUP_NAME_ID = "idSale";
    private static final String GROUP_NAME_CODE = "codeSale";
    private static final String GROUP_NAME_SALESMAN_NAME = "salesmanNameSale";

    public SaleFactory() { }

    public SaleModel create(Matcher matcher, List<ItemModel> itemModelList) {
        return SaleModel.builder()
                .withId(Long.parseLong(matcher.group(GROUP_NAME_ID)))
                .withCode(Long.parseLong(matcher.group(GROUP_NAME_CODE)))
                .withSalesmanName(matcher.group(GROUP_NAME_SALESMAN_NAME))
                .withTotalSale(BigDecimal.ZERO)
                .withItemList(itemModelList)
                .build();
    }
}
