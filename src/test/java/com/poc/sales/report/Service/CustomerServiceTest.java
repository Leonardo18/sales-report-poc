package com.poc.sales.report.Service;

import com.poc.sales.report.factory.CustomerFactory;
import com.poc.sales.report.model.CustomerModel;
import com.poc.sales.report.service.CustomerService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerFactory customerFactory;

    @Before
    public void setUp() {
        this.customerFactory = mock(CustomerFactory.class);
        this.customerService = new CustomerService(customerFactory);
    }

    @Test
    public void shouldReturnBuildCustomerByMatcherWithSuccess() {
        when(customerFactory.create(any())).thenReturn(buildCustomerModelWithAllfields());
        assertEquals(buildCustomerModelWithAllfields().toString(), customerService.buildCustomerByMatcher(any()).toString());
    }

    @Test
    public void shouldReturnTotalCustomerWithSuccess() {
        List<CustomerModel> customerModelList = new ArrayList<>();
        customerModelList.add(buildCustomerModelWithAllfields());
        assertEquals(Integer.valueOf(1), customerService.getTotalCustomer(customerModelList));
    }

    private CustomerModel buildCustomerModelWithAllfields() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(002L);
        customerModel.setTaxId("2345675434544345");
        customerModel.setName("Jose da Silva");
        customerModel.setBusinessArea("Rural");

        return customerModel;
    }
}
