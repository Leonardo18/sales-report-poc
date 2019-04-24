package com.poc.sales.report.Service;

import com.poc.sales.report.model.CustomerModel;
import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;
import com.poc.sales.report.model.SalesmanModel;
import com.poc.sales.report.service.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SalesReportServiceTest {

    private SalesReportService salesReportService;
    private CustomerService customerService;
    private SaleService saleService;
    private SalesmanService salesmanService;
    private OutputFileService outputFileService;

    @Before
    public void setUp() {
        this.customerService = mock(CustomerService.class);
        this.saleService = mock(SaleService.class);
        this.salesmanService = mock(SalesmanService.class);
        this.outputFileService = mock(OutputFileService.class);
        this.salesReportService = new SalesReportService(customerService, saleService, salesmanService, outputFileService);
    }

    @Test
    public void shouldProcessFileWithSuccess() throws Exception {
        when(customerService.buildCustomerByMatcher(any())).thenReturn(buildCustomerModelWithAllfields());
        when(saleService.buildSaleByMatcher(any())).thenReturn(buildSaleModelWithAllfields());
        when(salesmanService.buildSalesmanByMatcher(any())).thenReturn(buildSalesmanModelWithAllfields(buildSaleModelList()));
        when(salesmanService.setSalesmanSales(any(), any())).thenReturn(buildSalesmanModelList());
        when(customerService.getTotalCustomer(any())).thenReturn(1);
        when(salesmanService.getTotalSalesman(any())).thenReturn(1);
        when(saleService.getSaleMoreExpensive(any())).thenReturn(buildSaleModelWithAllfields());
        when(salesmanService.getWorstSalesman(any())).thenReturn(buildSalesmanModelWithAllfields(buildSaleModelList()));
        doNothing().when(outputFileService).processSalesReportFile(any(), any(), any(), any(), any());

        salesReportService.processInputFileData(getFileByName("sale2.dat"));
    }

    @Test
    public void shouldProcessFileWithInvalidFile() throws Exception {
        salesReportService.processInputFileData(getFileByName("sale_invalid_file_content.dat"));
    }

    private SalesmanModel buildSalesmanModelWithAllfields(List<SaleModel> saleModelList) {
        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setId(001L);
        salesmanModel.setTaxId("1234567891234");
        salesmanModel.setName("Pedro");
        salesmanModel.setSalary(new BigDecimal(50000));
        salesmanModel.setTotalSalesValue(BigDecimal.ZERO);
        salesmanModel.setSaleModelList(saleModelList);
        return salesmanModel;
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

    private CustomerModel buildCustomerModelWithAllfields() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(002L);
        customerModel.setTaxId("2345675434544345");
        customerModel.setName("Jose da Silva");
        customerModel.setBusinessArea("Rural");

        return customerModel;
    }

    private List<SalesmanModel> buildSalesmanModelList() {
        List<SalesmanModel> salesmanModelList = new ArrayList<>();
        salesmanModelList.add(buildSalesmanModelWithAllfields(buildSaleModelList()));
        return salesmanModelList;
    }

    private List<SaleModel> buildSaleModelList() {
        List<SaleModel> saleModelList = new ArrayList<>();
        saleModelList.add(buildSaleModelWithAllfields());
        return saleModelList;
    }

    private List<CustomerModel> buildCustomerModelList() {
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(buildCustomerModelWithAllfields());
        return customerModelList;
    }

    private File getFileByName(String fileName) {
        return new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile()
        );
    }
}
