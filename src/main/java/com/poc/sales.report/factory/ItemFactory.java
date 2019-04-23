package com.poc.sales.report.factory;

import com.poc.sales.report.model.ItemModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Matcher;

@Component
public class ItemFactory {

    private static final String GROUP_NAME_ID = "idItem";
    private static final String GROUP_NAME_QUANTITY = "quantityItem";
    private static final String GROUP_NAME_PRICE = "priceItem";

    public ItemModel create(Matcher matcher) {
        return ItemModel.getBuilder()
                .withId(Long.parseLong(matcher.group(GROUP_NAME_ID)))
                .withQuantity(Integer.parseInt(matcher.group(GROUP_NAME_QUANTITY)))
                .withPrice(new BigDecimal(matcher.group(GROUP_NAME_PRICE)))
                .build();
    }
}
