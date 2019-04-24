package com.poc.sales.report.service;

import com.poc.sales.report.model.CustomerModel;
import com.poc.sales.report.model.SaleModel;
import com.poc.sales.report.model.SalesmanModel;
import org.springframework.dao.EmptyResultDataAccessException;
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

    private CustomerService customerService;
    private SaleService saleService;
    private SalesmanService salesmanService;
    private OutputFileService outputFileService;

    private List<SalesmanModel> salesmanModelList;
    private List<CustomerModel> customerModelList;
    private List<SaleModel> saleModelList;

    public SalesReportService(CustomerService customerService,
                              SaleService saleService,
                              SalesmanService salesmanService,
                              OutputFileService outputFileService) {
        this.customerService = customerService;
        this.saleService = saleService;
        this.salesmanService = salesmanService;
        this.outputFileService = outputFileService;
    }

    public void processInputFileData(File file) {
        try {
            Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()));

            salesmanModelList = new ArrayList<>();
            customerModelList = new ArrayList<>();
            saleModelList = new ArrayList<>();

            stream.forEach(line -> {
               if (ParseFileContentService.checkExpression(line, ParseFileContentService.REGEX_SALESMAN)) {
                   fetchSalesmanList(ParseFileContentService.getResult());
               } else if (ParseFileContentService.checkExpression(line, ParseFileContentService.REGEX_CUSTOMER)) {
                   fetchCustomerList(ParseFileContentService.getResult());
               } else if (ParseFileContentService.checkExpression(line, ParseFileContentService.REGEX_SALE)) {
                   fetchSaleList(ParseFileContentService.getResult());
               }
            });

            if(Stream.of(salesmanModelList, customerModelList, saleModelList).allMatch(List::isEmpty))
                throw new Exception("File cannot be processed, invalid content.");

            salesmanModelList = salesmanService.setSalesmanSales(salesmanModelList, saleModelList);

            outputFileService.processSalesReportFile(
                    file,
                    customerService.getTotalCustomer(customerModelList),
                    salesmanService.getTotalSalesman(salesmanModelList),
                    saleService.getSaleMoreExpensive(saleModelList),
                    salesmanService.getWorstSalesman(salesmanModelList)
             );

            System.out.println("File processed with success.");

        } catch (Exception ex) {
            System.out.println(
                    "Error to proccess file, please verify file content and try again. File: "
                            .concat(file.getAbsolutePath())
                            .concat(" Error Message: ")
                            .concat(ex.getMessage())
            );
        }
    }

    private void fetchSalesmanList(Matcher matcher) {
        salesmanModelList.add(salesmanService.buildSalesmanByMatcher(matcher));
    }

    private void fetchCustomerList(Matcher matcher) {
        customerModelList.add(customerService.buildCustomerByMatcher(matcher));
    }

    private void fetchSaleList(Matcher matcher) {
        saleModelList.add(saleService.buildSaleByMatcher(matcher));
    }

}
