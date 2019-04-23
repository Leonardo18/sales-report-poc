package com.poc.sales.report.builder;

import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleBuilderTest {

    private SaleBuilder saleBuilder;

    @Before
    public void setUp() {
        this.saleBuilder = new SaleBuilder();
    }

    @Test
    public void shouldBuildItemWithAllPropertiesWithSuccess() {
        SaleModel saleModel = saleBuilder
                .withId(1L)
                .withCode(2L)
                .withSalesmanName("Pedro")
                .withItemList(buildItemModelListWithAllfields())
                .withTotalSale(new BigDecimal(1000))
                .build();
        Assert.assertEquals(buildSaleModelWithAllfields().toString(), saleModel.toString());
    }


    @Test
    public void shouldBuildItemFromWithOnlyOnePropertieWithSuccess() {
        SaleModel saleModel = saleBuilder
                .withCode(2L)
                .build();
        Assert.assertEquals(buildSaleModelWithOnlyOnefield().toString(), saleModel.toString());
    }

    private SaleModel buildSaleModelWithAllfields() {
        SaleModel saleModel = new SaleModel();
        saleModel.setId(1L);
        saleModel.setCode(2L);
        saleModel.setSalesmanName("Pedro");
        saleModel.setItemModelList(buildItemModelListWithAllfields());
        saleModel.setTotalSale(new BigDecimal(1000));
        return saleModel;
    }

    private SaleModel buildSaleModelWithOnlyOnefield() {
        SaleModel saleModel = new SaleModel();
        saleModel.setCode(2L);
        return saleModel;
    }

    private List<ItemModel> buildItemModelListWithAllfields() {
        List<ItemModel> itemModelList = new ArrayList<>();

        ItemModel itemModel = new ItemModel();
        itemModel.setId(1L);
        itemModel.setPrice(new BigDecimal(2.22));
        itemModel.setQuantity(10);
        itemModelList.add(itemModel);

        return itemModelList;
    }
}
