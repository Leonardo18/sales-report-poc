package com.poc.sales.report.factory;

import com.poc.sales.report.model.SalesmanModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.regex.Matcher;

public class SalesmanFactoryTest extends FactoryBaseTest {

    private SalesmanFactory salesmanFactory;

    @Before
    public void setUp() {
        this.salesmanFactory = new SalesmanFactory();
    }

    @Test
    public void shouldBuildCustomerFromFactoryWithSuccess() {
        String line = "001ç1234567891234çPedroç50000";
        Matcher matcher = getMatcherByExpression(line, REGEX_SALESMAN);
        matcher.find();
        SalesmanModel salesmanModel = salesmanFactory.create(matcher);
        Assert.assertEquals(buildSalesmanModelWithAllfields().toString(), salesmanModel.toString());

    }

    private SalesmanModel buildSalesmanModelWithAllfields() {
        SalesmanModel salesmanModel = new SalesmanModel();
        salesmanModel.setId(001L);
        salesmanModel.setTaxId("1234567891234");
        salesmanModel.setName("Pedro");
        salesmanModel.setSalary(new BigDecimal(50000));
        salesmanModel.setTotalSalesValue(BigDecimal.ZERO);
        return salesmanModel;
    }
}
