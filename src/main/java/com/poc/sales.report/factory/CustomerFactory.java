package com.poc.sales.report.factory;

import com.poc.sales.report.model.CustomerModel;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;

@Component
public class CustomerFactory {

    private static final String GROUP_NAME_ID = "idCustomer";
    private static final String GROUP_NAME_TAX_ID = "taxIdCustomer";
    private static final String GROUP_NAME_NAME = "nameCustomer";
    private static final String GROUP_NAME_BUSINESS_AREA = "businessAreaCustomer";

    public CustomerModel create(Matcher matcher){
        return CustomerModel.getBuilder()
                .withId(Long.parseLong(matcher.group(GROUP_NAME_ID)))
                .withTaxId(matcher.group(GROUP_NAME_TAX_ID))
                .withName(matcher.group(GROUP_NAME_NAME))
                .withBusinessArea(matcher.group(GROUP_NAME_BUSINESS_AREA))
                .build();
    }
}
