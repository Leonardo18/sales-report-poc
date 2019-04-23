package com.poc.sales.report.service;

import com.poc.sales.report.model.CustomerModel;
import com.poc.sales.report.model.SaleModel;
import com.poc.sales.report.model.SalesmanModel;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

@Service
public class SalesReportService {

    private CustomerService customerService;
    private SaleService saleService;
    private SalesmanService salesmanService;

    private List<SalesmanModel> salesmanModelList;
    private List<CustomerModel> customerModelList;
    private List<SaleModel> saleModelList;

    private static final String BASE_DIRECTORY_PATH = System.getProperty("user.home");
    private static final String SALES_REPORT_DEST_PATH = "/data/out/";
    private static final String SALES_REPORT_DEST_FILE_NAME = ".done.dat";

    public SalesReportService(CustomerService customerService, SaleService saleService, SalesmanService salesmanService) {
        this.customerService = customerService;
        this.saleService = saleService;
        this.salesmanService = salesmanService;
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

            salesmanModelList = salesmanService.setSalesmanSales(salesmanModelList, saleModelList);
            processSalesReportFile(getSourceFileNameWithoutExtension(file));
            System.out.println("File processed with success.");
            file.delete();

        } catch (Exception ex) {
            System.out.println("Error to proccess file, please verify file content and try again. File:" + file.getAbsolutePath());
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

    private void processSalesReportFile (String sourceFileName) throws Exception {
        List<String> reportLines = Arrays.asList(
                buildTotalCustomersReportLine(),
                buildTotalSalesmansReportLine(),
                buildSalesMoreExpensiveReportLine(),
                buildWorstSalesmanReportLine()
        );

        Path file = Paths.get(
                BASE_DIRECTORY_PATH
                        .concat(SALES_REPORT_DEST_PATH)
                        .concat(sourceFileName)
                        .concat(SALES_REPORT_DEST_FILE_NAME)
        );
        Files.write(file, reportLines, Charset.forName("UTF-8"));
    }

    private String buildTotalCustomersReportLine() {
        return new StringBuilder()
                .append("Total Customers: ")
                .append(customerService.getTotalCustomer(customerModelList))
                .toString();
    }

    private String buildTotalSalesmansReportLine() {
        return new StringBuilder()
                .append("Total Sellers: ")
                .append(salesmanService.getTotalSalesman(salesmanModelList))
                .toString();
    }

    private String buildSalesMoreExpensiveReportLine() {
        SaleModel saleModel = saleService.getSaleMoreExpensive(saleModelList);
        return new StringBuilder()
                .append("Sale most expensive. Sale Code: ")
                .append(saleModel.getCode())
                .append(" Total value of sale: ")
                .append(saleModel.getTotalSale())
                .toString();
    }

    private String buildWorstSalesmanReportLine() {
        SalesmanModel salesmanModel = salesmanService.getWorstSalesman(salesmanModelList);
        return new StringBuilder()
                .append("Worst Salesman : ")
                .append(salesmanModel.getName())
                .append(" with sales value of: ")
                .append(salesmanModel.getTotalSalesValue())
                .toString();
    }

    private String getSourceFileNameWithoutExtension(File file) {
        return FilenameUtils.removeExtension(file.getName());
    }
}
