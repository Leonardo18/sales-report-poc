package com.poc.sales.report.factory;

import com.poc.sales.report.model.CustomerModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFactoryTest extends FactoryBaseTest {

    private CustomerFactory customerFactory;

    @Before
    public void setUp() {
        this.customerFactory = new CustomerFactory();
    }

    @Test
    public void shouldBuildCustomerFromFactoryWithSuccess() {
        String line = "002ç2345675434544345çJose da SilvaçRural";
        Matcher matcher = getMatcherByExpression(line, REGEX_CUSTOMER);
        matcher.find();
        CustomerModel customerModel = customerFactory.create(matcher);
        Assert.assertEquals(buildCustomerModelWithAllfields().toString(), customerModel.toString());

    }

    private CustomerModel buildCustomerModelWithAllfields() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(002L);
        customerModel.setTaxId("2345675434544345");
        customerModel.setName("Jose da Silva");
        customerModel.setBusinessArea("Rural");
        return customerModel;
    }

    public static Matcher getMatcherByExpression(String line, String expression) {
        Pattern pattern = Pattern.compile(expression);
        Matcher result = pattern.matcher(line);
        return result;
    }
}
