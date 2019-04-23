package com.poc.sales.report.Service;

import com.poc.sales.report.factory.SalesmanFactory;
import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;
import com.poc.sales.report.model.SalesmanModel;
import com.poc.sales.report.service.SalesmanService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SalesmanServiceTest {

    private SalesmanService salesmanService;
    private SalesmanFactory salesmanFactory;

    @Before
    public void setUp() {
        this.salesmanFactory = mock(SalesmanFactory.class);
        this.salesmanService = new SalesmanService(salesmanFactory);
    }

    @Test
    public void shouldBuildSalesmanByMatcherWithSuccess() {
        when(salesmanFactory.create(any())).thenReturn(buildSalesmanModelWithAllfields(BigDecimal.ZERO, null));
        assertEquals(buildSalesmanModelWithAllfields(BigDecimal.ZERO, null).toString(), salesmanService.buildSalesmanByMatcher(any()).toString());
    }

    @Test
    public void shouldSetSalesmanSalesWithSuccess() {
        List<SalesmanModel> salesmanModelList = new ArrayList<>();
        List<SaleModel> saleModelList = new ArrayList<>();
        saleModelList.add(buildSaleModelWithAllfields(1L, BigDecimal.valueOf(1000)));
        saleModelList.add(buildSaleModelWithAllfields(2L, BigDecimal.valueOf(5000)));
        salesmanModelList.add(buildSalesmanModelWithAllfields(BigDecimal.valueOf(6000), saleModelList));
        assertEquals(salesmanModelList.toString(), salesmanService.setSalesmanSales(salesmanModelList, saleModelList).toString());
    }

    @Test
    public void shouldGetTotalSalesmanWithSucces() {
        List<SalesmanModel> salesmanModelList = new ArrayList<>();
        salesmanModelList.add(buildSalesmanModelWithAllfields(BigDecimal.valueOf(6000), null));
        salesmanModelList.add(buildSalesmanModelWithAllfields(BigDecimal.valueOf(6000), null));
        assertEquals(Integer.valueOf(2), salesmanService.getTotalSalesman(salesmanModelList));
    }

    @Test
    public void shouldGetWorstSalesman() {
        List<SalesmanModel> salesmanModelList = new ArrayList<>();
        salesmanModelList.add(buildSalesmanModelWithAllfields(BigDecimal.valueOf(6000), null));
        salesmanModelList.add(buildSalesmanModelWithAllfields(BigDecimal.valueOf(4000), null));
        assertEquals(buildSalesmanModelWithAllfields(BigDecimal.valueOf(4000), null).toString(), salesmanService.getWorstSalesman(salesmanModelList).toString());
    }

    private SalesmanModel buildSalesmanModelWithAllfields(BigDecimal totalSalesValue, List<SaleModel> saleModelList) {
        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setId(001L);
        salesmanModel.setTaxId("1234567891234");
        salesmanModel.setName("Pedro");
        salesmanModel.setSalary(new BigDecimal(50000));
        salesmanModel.setTotalSalesValue(totalSalesValue);
        salesmanModel.setSaleModelList(saleModelList);
        return salesmanModel;
    }

    private SaleModel buildSaleModelWithAllfields(Long code, BigDecimal TotalSale) {
        SaleModel saleModel = new SaleModel();
        saleModel.setId(003L);
        saleModel.setCode(code);
        saleModel.setSalesmanName("Pedro");
        saleModel.setTotalSale(TotalSale);
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
