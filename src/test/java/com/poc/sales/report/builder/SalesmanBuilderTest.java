package com.poc.sales.report.builder;

import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;
import com.poc.sales.report.model.SalesmanModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SalesmanBuilderTest {

    private SalesmanBuilder salesmanBuilder;

    @Before
    public void setUp() {
        this.salesmanBuilder = new SalesmanBuilder();
    }

    @Test
    public void shouldBuildSalesmanWithAllPropertiesWithSuccess() {
        SalesmanModel salesmanModel = salesmanBuilder
                .withId(1L)
                .withTaxId("63772221300")
                .withName("Pedro")
                .withSalary(new BigDecimal(4000))
                .withTotalSalesValue(new BigDecimal(5000))
                .withSaleList(buildSaleModelListWithAllfields())
                .build();
        Assert.assertEquals(buildSalesmanModelWithAllfields().toString(), salesmanModel.toString());
    }


    @Test
    public void shouldBuildItemFromWithOnlyOnePropertieWithSuccess() {
        SalesmanModel salesmanModel = salesmanBuilder
                .withTaxId("63772221300")
                .build();
        Assert.assertEquals(buildSalesmanModelWithOnlyOnefield().toString(), salesmanModel.toString());
    }

    private SalesmanModel buildSalesmanModelWithAllfields() {
        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setId(1L);
        salesmanModel.setTaxId("63772221300");
        salesmanModel.setName("Pedro");
        salesmanModel.setSalary(new BigDecimal(4000));
        salesmanModel.setTotalSalesValue(new BigDecimal(5000));
        salesmanModel.setSaleModelList(buildSaleModelListWithAllfields());
        return salesmanModel;
    }

    private SalesmanModel buildSalesmanModelWithOnlyOnefield() {
        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setTaxId("63772221300");
        return salesmanModel;
    }

    private List<SaleModel> buildSaleModelListWithAllfields() {
        List<SaleModel> saleModelList = new ArrayList<>();

        SaleModel saleModel = new SaleModel();
        saleModel.setId(1L);
        saleModel.setCode(2L);
        saleModel.setSalesmanName("Pedro");
        saleModel.setItemModelList(buildItemModelListWithAllfields());
        saleModel.setTotalSale(new BigDecimal(1000));

        saleModelList.add(saleModel);
        return saleModelList;
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
