package com.poc.sales.report.factory;

import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class SaleFactoryTest extends FactoryBaseTest {

    private SaleFactory saleFactory;

    @Before
    public void setUp() {
        this.saleFactory = new SaleFactory();
    }

    @Test
    public void shouldBuildSaleFromFactoryWithSuccess() {
        String line = "003ç10ç[1-10-100]çPedro";
        Matcher matcher = getMatcherByExpression(line, REGEX_SALE);
        matcher.find();
        SaleModel saleModel = saleFactory.create(matcher, buildItemModelListWithAllfields());
        Assert.assertEquals(buildSaleModelWithAllfields().toString(), saleModel.toString());

    }

    private SaleModel buildSaleModelWithAllfields() {
        SaleModel saleModel = new SaleModel();
        saleModel.setId(003L);
        saleModel.setCode(10L);
        saleModel.setSalesmanName("Pedro");
        saleModel.setTotalSale(BigDecimal.ZERO);
        saleModel.setItemModelList(buildItemModelListWithAllfields());
        return saleModel;
    }

    private List<ItemModel> buildItemModelListWithAllfields() {
        List<ItemModel> itemModelList = new ArrayList<>();

        ItemModel itemModel = new ItemModel();
        itemModel.setId(1L);
        itemModel.setPrice(new BigDecimal(100));
        itemModel.setQuantity(10);
        itemModelList.add(itemModel);

        return itemModelList;
    }
}
