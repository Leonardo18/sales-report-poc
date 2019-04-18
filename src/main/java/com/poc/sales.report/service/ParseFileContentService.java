package com.poc.sales.report.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class ParseFileContentService {
    public static final String REGEX_SALESMAN = "^(?<idSalesname>001)ç(?<taxIdSalesname>[0-9]{13})ç(?<nameSalesname>[\\s\\S]+)ç(?<salarySalesname>[0-9]*\\.?[0-9]+)";
    public static final String REGEX_CUSTOMER = "^(?<idCustomer>002)ç(?<taxIdCustomer>[0-9]{16})ç(?<nameCustomer>[\\s\\S]+)ç(?<businessAreaCustomer>[\\s\\S]+)";
    public static final String REGEX_SALE = "^(?<idSale>003)ç(?<codeSale>[0-9]{0,})ç(?<itemSale>\\[[0-9\\-\\,\\.]+\\])ç(?<salesmanNameSale>[\\s\\S]+)";
    public static final String REGEX_ITEM = "((?<idItem>[0-9]+)-(?<quantityItem>[0-9]+)-(?<priceItem>[0-9]*\\.?[0-9]+))+";

    private static Matcher result;

    public static Boolean checkExpression(String line, String expression) {
        try {
            Pattern pattern = Pattern.compile(expression);
            result = pattern.matcher(line);
            if (result.find())
                return true;
        }catch (Exception e) {
            throw new PatternSyntaxException("Regular expression did not match", expression, -1);
        }
        return false;
    }

    public static Matcher getResult() { return result; }
}
