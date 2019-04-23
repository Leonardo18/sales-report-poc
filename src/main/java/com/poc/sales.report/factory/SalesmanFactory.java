package com.poc.sales.report.factory;

import com.poc.sales.report.model.SalesmanModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Matcher;

@Component
public class SalesmanFactory {

    private static final String GROUP_NAME_ID = "idSalesname";
    private static final String GROUP_NAME_TAX_ID = "taxIdSalesname";
    private static final String GROUP_NAME_NAME = "nameSalesname";
    private static final String GROUP_NAME_SALARY = "salarySalesname";

    public SalesmanFactory() { }

    public SalesmanModel create(Matcher matcher) {
        return SalesmanModel.getBuilder()
                .withId(Long.parseLong(matcher.group(GROUP_NAME_ID)))
                .withTaxId(matcher.group(GROUP_NAME_TAX_ID))
                .withName(matcher.group(GROUP_NAME_NAME))
                .withTotalSalesValue(BigDecimal.ZERO)
                .withSalary(new BigDecimal(matcher.group(GROUP_NAME_SALARY)))
                .build();
    }
}
