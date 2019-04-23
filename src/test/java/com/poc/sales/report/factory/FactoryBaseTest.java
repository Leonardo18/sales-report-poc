package com.poc.sales.report.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FactoryBaseTest {

    static final String REGEX_CUSTOMER = "^(?<idCustomer>002)ç(?<taxIdCustomer>[0-9]{16})ç(?<nameCustomer>[\\s\\S]+)ç(?<businessAreaCustomer>[\\s\\S]+)";
    static final String REGEX_ITEM = "((?<idItem>[0-9]+)-(?<quantityItem>[0-9]+)-(?<priceItem>[0-9]*\\.?[0-9]+))+";
    static final String REGEX_SALE = "^(?<idSale>003)ç(?<codeSale>[0-9]{0,})ç(?<itemSale>\\[[0-9\\-\\,\\.]+\\])ç(?<salesmanNameSale>[\\s\\S]+)";
    static final String REGEX_SALESMAN = "^(?<idSalesname>001)ç(?<taxIdSalesname>[0-9]{13})ç(?<nameSalesname>[\\s\\S]+)ç(?<salarySalesname>[0-9]*\\.?[0-9]+)";

    public static Matcher getMatcherByExpression(String line, String expression) {
        Pattern pattern = Pattern.compile(expression);
        Matcher result = pattern.matcher(line);
        return result;
    }
}
