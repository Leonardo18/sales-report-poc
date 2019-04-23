package com.poc.sales.report.builder;

import com.poc.sales.report.model.CustomerModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerBuilderTest {

    private CustomerBuilder customerBuilder;

    @Before
    public void setUp() {
        this.customerBuilder = new CustomerBuilder();
    }

    @Test
    public void shouldBuildCustomerWithAllPropertiesWithSuccess() {
        CustomerModel customerModel = customerBuilder
            .withId(1L)
            .withTaxId("56306923802")
            .withName("Dale ale")
            .withBusinessArea("Office")
            .build();
        Assert.assertEquals(buildCustomerModelWithAllfields().toString(), customerModel.toString());
    }


    @Test
    public void shouldBuildCustomerWithOnlyOnePropertieWithSuccess() {
        CustomerModel customerModel = customerBuilder
                .withName("Dale ale")
                .build();
        Assert.assertEquals(buildCustomerModelWithOnlyOneField().toString(), customerModel.toString());
    }

    private CustomerModel buildCustomerModelWithAllfields() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(1L);
        customerModel.setTaxId("56306923802");
        customerModel.setName("Dale ale");
        customerModel.setBusinessArea("Office");
        return customerModel;
    }

    private CustomerModel buildCustomerModelWithOnlyOneField() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setName("Dale ale");
        return customerModel;
    }
}
