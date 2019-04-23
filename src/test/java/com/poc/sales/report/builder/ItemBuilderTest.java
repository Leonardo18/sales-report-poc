package com.poc.sales.report.builder;

import com.poc.sales.report.model.ItemModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ItemBuilderTest {

    private ItemBuilder itemBuilder;

    @Before
    public void setUp() {
        this.itemBuilder = new ItemBuilder();
    }

    @Test
    public void shouldBuildItemWithAllPropertiesWithSuccess() {
        ItemModel itemModel = itemBuilder
                .withId(1L)
                .withPrice(new BigDecimal(2.22))
                .withQuantity(10)
                .build();
        Assert.assertEquals(buildItemModelWithAllfields().toString(), itemModel.toString());
    }


    @Test
    public void shouldBuildItemFromWithOnlyOnePropertieWithSuccess() {
        ItemModel itemModel = itemBuilder
                .withPrice(new BigDecimal(2.22))
                .build();
        Assert.assertEquals(buildItemModelWithOnlyOnefield().toString(), itemModel.toString());
    }

    private ItemModel buildItemModelWithAllfields() {
        ItemModel itemModel = new ItemModel();
        itemModel.setId(1L);
        itemModel.setPrice(new BigDecimal(2.22));
        itemModel.setQuantity(10);
        return itemModel;
    }

    private ItemModel buildItemModelWithOnlyOnefield() {
        ItemModel itemModel = new ItemModel();
        itemModel.setPrice(new BigDecimal(2.22));
        return itemModel;
    }
}
