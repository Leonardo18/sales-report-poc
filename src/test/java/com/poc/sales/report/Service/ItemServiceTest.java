package com.poc.sales.report.Service;

import com.poc.sales.report.factory.ItemFactory;
import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.service.ItemService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemServiceTest {

    private ItemService itemService;
    private ItemFactory itemFactory;

    @Before
    public void setUp() {
        this.itemFactory = mock(ItemFactory.class);
        this.itemService = new ItemService(itemFactory);
    }

    @Test
    public void shouldBuildItemListBySaleWithSuccess() {
        String itemSale = "[1-10-100]";
        when(itemFactory.create(any())).thenReturn(buildItemModelWithAllfields());
        assertEquals(buildItemModelListWithAllfields().toString(), itemService.buildItemListBySale(itemSale).toString());

    }

    private ItemModel buildItemModelWithAllfields() {
        ItemModel itemModel = new ItemModel();
        itemModel.setId(1L);
        itemModel.setPrice(new BigDecimal(100));
        itemModel.setQuantity(10);
        return itemModel;
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
