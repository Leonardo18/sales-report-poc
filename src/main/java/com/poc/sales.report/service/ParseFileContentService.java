package com.poc.sales.report.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
class ParseFileContentService {
    static final String REGEX_SALESMAN = "^(?<idSalesname>001)ç(?<taxIdSalesname>[0-9]{13})ç(?<nameSalesname>[\\s\\S]+)ç(?<salarySalesname>[0-9]*\\.?[0-9]+)";
    static final String REGEX_CUSTOMER = "^(?<idCustomer>002)ç(?<taxIdCustomer>[0-9]{16})ç(?<nameCustomer>[\\s\\S]+)ç(?<businessAreaCustomer>[\\s\\S]+)";
    static final String REGEX_SALE = "^(?<idSale>003)ç(?<codeSale>[0-9]{0,})ç(?<itemSale>\\[[0-9\\-\\,\\.]+\\])ç(?<salesmanNameSale>[\\s\\S]+)";
    static final String REGEX_ITEM = "((?<idItem>[0-9]+)-(?<quantityItem>[0-9]+)-(?<priceItem>[0-9]*\\.?[0-9]+))+";

    private static Matcher result;

    static Boolean checkExpression(String line, String expression) {
        try {
            Pattern pattern = Pattern.compile(expression);
            result = pattern.matcher(line);
            if (result.find())
                return true;
        } catch (Exception e) {
            throw new PatternSyntaxException("Regular expression did not match", expression, -1);
        }
        return false;
    }

    public static Matcher getMatcherByExpression(String line, String expression) {
        Pattern pattern = Pattern.compile(expression);
        result = pattern.matcher(line);
        return result;
    }

    static Matcher getResult() { return result; }
}
