package com.poc.sales.report.service;

import com.poc.sales.report.factory.ItemFactory;
import com.poc.sales.report.model.ItemModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@Service
public class ItemService {

    private ItemFactory itemFactory;

    public ItemService(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public List<ItemModel> buildItemListBySale(String itemSaleGroup) {
        List<ItemModel> itemModelList = new ArrayList<>();
        Matcher matcher = ParseFileContentService.getMatcherByExpression(itemSaleGroup, ParseFileContentService.REGEX_ITEM);

        while (matcher.find())
            itemModelList.add(itemFactory.create(matcher));

        return itemModelList;
    }
}
