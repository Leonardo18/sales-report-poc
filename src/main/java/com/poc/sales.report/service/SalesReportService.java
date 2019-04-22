package com.poc.sales.report.service;

import com.poc.sales.report.factory.CustomerFactory;
import com.poc.sales.report.factory.ItemFactory;
import com.poc.sales.report.factory.SaleFactory;
import com.poc.sales.report.model.CustomerModel;
import com.poc.sales.report.model.ItemModel;
import com.poc.sales.report.model.SaleModel;
import com.poc.sales.report.model.SalesmanModel;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

@Service
public class SalesReportService {

    public void proccessFile(File file) {
        try {
            Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()));

            stream.forEach(line -> {
               if (ParseFileContentService.checkExpression(line, ParseFileContentService.REGEX_SALESMAN)) {

               } else if (ParseFileContentService.checkExpression(line, ParseFileContentService.REGEX_CUSTOMER)) {
                   buildCustomerList(ParseFileContentService.getResult());
               } else if (ParseFileContentService.checkExpression(line, ParseFileContentService.REGEX_SALE)) {
                    buildSaleList(ParseFileContentService.getResult());
               }
                System.out.println(ParseFileContentService.getResult().toMatchResult());
            });
        } catch (Exception ex) {
            System.out.println("Error to proccess file, please verify file content and try again. File:" + file.getAbsolutePath());
        }
    }

    private List<CustomerModel> buildCustomerList(Matcher matcher) {
        List<CustomerModel> customerModelList = new ArrayList<>();
        CustomerFactory customerFactory = new CustomerFactory();
        customerModelList.add(customerFactory.create(matcher));
        return customerModelList;
    }

    private List<SaleModel> buildSaleList(Matcher matcher) {
        List<SaleModel> saleModelList = new ArrayList<>();
        SaleFactory saleFactory = new SaleFactory();

        ParseFileContentService.checkExpression(matcher.group("itemSale"), ParseFileContentService.REGEX_ITEM);

        saleModelList.add(saleFactory.create(matcher, buildItemList()));
        return saleModelList;
    }

    private List<ItemModel> buildItemList() {
        List<ItemModel> itemModelList = new ArrayList<>();
        ItemFactory itemFactory = new ItemFactory();

        return new ArrayList<>();
    }
}
