package com.poc.sales.report.service;

import com.poc.sales.report.model.SaleModel;
import com.poc.sales.report.model.SalesmanModel;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class OutputFileService {

    private static final String BASE_DIRECTORY_PATH = System.getProperty("user.home");
    private static final String SALES_REPORT_DEST_PATH = "/data/out/";
    private static final String SALES_REPORT_DEST_FILE_NAME = ".done.dat";

    public void processSalesReportFile (File sourceFile,
                                        Integer totalCustomers,
                                        Integer totalSalesman,
                                        SaleModel saleModel,
                                        SalesmanModel salesmanModel) throws Exception {
        List<String> reportLines = Arrays.asList(
                buildTotalCustomersReportLine(totalCustomers),
                buildTotalSalesmansReportLine(totalSalesman),
                buildSalesMoreExpensiveReportLine(saleModel),
                buildWorstSalesmanReportLine(salesmanModel)
        );

        Path file = Paths.get(
                BASE_DIRECTORY_PATH
                        .concat(SALES_REPORT_DEST_PATH)
                        .concat(getSourceFileNameWithoutExtension(sourceFile))
                        .concat(SALES_REPORT_DEST_FILE_NAME)
        );
        Files.write(file, reportLines, Charset.forName("UTF-8"));

        sourceFile.delete();
    }

    private String buildTotalCustomersReportLine(Integer totalCustomers) {
        return new StringBuilder()
                .append("Total Customers: ")
                .append(totalCustomers)
                .toString();
    }

    private String buildTotalSalesmansReportLine(Integer totalSalesman) {
        return new StringBuilder()
                .append("Total Sellers: ")
                .append(totalSalesman)
                .toString();
    }

    private String buildSalesMoreExpensiveReportLine(SaleModel saleModel) {
        return new StringBuilder()
                .append("Sale most expensive. Sale Code: ")
                .append(saleModel.getCode())
                .append(" Total value of sale: ")
                .append(saleModel.getTotalSale())
                .toString();
    }

    private String buildWorstSalesmanReportLine(SalesmanModel salesmanModel) {
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
