package com.poc.sales.report.factory;

import com.poc.sales.report.model.ItemModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.regex.Matcher;

public class ItemFactoryTest extends FactoryBaseTest{

    private ItemFactory itemFactory;

    @Before
    public void setUp() {
        this.itemFactory = new ItemFactory();
    }

    @Test
    public void shouldBuildItemFromFactoryWithSuccess() {
        String line = "[1-10-100]";
        Matcher matcher = getMatcherByExpression(line, REGEX_ITEM);
        matcher.find();
        ItemModel itemModel = itemFactory.create(matcher);
        Assert.assertEquals(buildItemModelWithAllfields().toString(), itemModel.toString());

    }

    private ItemModel buildItemModelWithAllfields() {
        ItemModel itemModel = new ItemModel();
        itemModel.setId(1L);
        itemModel.setPrice(new BigDecimal(100));
        itemModel.setQuantity(10);
        return itemModel;
    }
}
