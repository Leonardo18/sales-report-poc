package com.poc.sales.report.Service;

import com.poc.sales.report.factory.SaleFactory;
import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;
import com.poc.sales.report.service.ItemService;
import com.poc.sales.report.service.SaleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaleServiceTest {

    private SaleService saleService;
    private SaleFactory saleFactory;
    private ItemService itemService;
    private static final String REGEX_SALE = "^(?<idSale>003)ç(?<codeSale>[0-9]{0,})ç(?<itemSale>\\[[0-9\\-\\,\\.]+\\])ç(?<salesmanNameSale>[\\s\\S]+)";

    @Before
    public void setUp() {
        this.saleFactory = mock(SaleFactory.class);
        this.itemService = mock(ItemService.class);
        this.saleService = new SaleService(saleFactory, itemService);
    }

    @Test
    public void shouldBuildSaleByMatcherWithSuccess() {
        Matcher matcher = getMatcherByExpression("003ç10ç[1-10-100]çPedro", REGEX_SALE);
        matcher.find();
        when(saleFactory.create(any(), any())).thenReturn(buildSaleModelWithAllfields(BigDecimal.valueOf(1000)));
        when(itemService.buildItemListBySale(any())).thenReturn(buildItemModelListWithAllfields());
        assertEquals(buildSaleModelWithAllfields(BigDecimal.valueOf(1000)).toString(), saleService.buildSaleByMatcher(matcher).toString());
    }

    @Test
    public void shouldGetSaleMoreExpensiveOfOneSaleWithSucces() {
        List<SaleModel> saleModelList = new ArrayList<>();
        saleModelList.add(buildSaleModelWithAllfields(BigDecimal.valueOf(1000)));
        Assert.assertEquals(BigDecimal.valueOf(1000), saleService.getSaleMoreExpensive(saleModelList).getTotalSale());
    }

    @Test
    public void shouldGetSaleMoreExpensiveWithSucces() {
        List<SaleModel> saleModelList = new ArrayList<>();
        saleModelList.add(buildSaleModelWithAllfields(BigDecimal.valueOf(1000)));
        saleModelList.add(buildSaleModelWithAllfields(BigDecimal.valueOf(5000)));
        Assert.assertEquals(BigDecimal.valueOf(5000), saleService.getSaleMoreExpensive(saleModelList).getTotalSale());
    }

    private SaleModel buildSaleModelWithAllfields(BigDecimal totalSaleValue) {
        SaleModel saleModel = new SaleModel();
        saleModel.setId(003L);
        saleModel.setCode(10L);
        saleModel.setSalesmanName("Pedro");
        saleModel.setTotalSale(totalSaleValue);
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

    private static Matcher getMatcherByExpression(String line, String expression) {
        Pattern pattern = Pattern.compile(expression);
        Matcher result = pattern.matcher(line);
        return result;
    }
}
