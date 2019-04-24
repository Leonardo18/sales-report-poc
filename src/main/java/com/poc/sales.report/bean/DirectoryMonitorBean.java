package com.poc.sales.report.bean;

import com.poc.sales.report.config.FileInputConfig;
import com.poc.sales.report.event.DirectoryListenerEvent;
import com.poc.sales.report.service.SalesReportService;
import com.poc.sales.report.service.ValidateFileService;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class DirectoryMonitorBean  {

    private static final String BASE_DIRECTORY_PATH = System.getProperty("user.home");

    private ValidateFileService validateFileService;
    private SalesReportService salesReportService;
    private FileInputConfig fileInputConfig;

    public DirectoryMonitorBean(ValidateFileService validateFileService,
                                SalesReportService salesReportService,
                                FileInputConfig fileInputConfig) {
        this.validateFileService = validateFileService;
        this.salesReportService = salesReportService;
        this.fileInputConfig = fileInputConfig;
    }

    @Bean
    public void initializeDirectoryMonitor() throws Exception{
        final File directory = new File(BASE_DIRECTORY_PATH.concat(fileInputConfig.getFileDirectoryPath()));

        FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(directory);
        fileAlterationObserver.addListener(new DirectoryListenerEvent(validateFileService, salesReportService));

        final FileAlterationMonitor monitor = new FileAlterationMonitor(fileInputConfig.getMonitorInterval());
        monitor.addObserver(fileAlterationObserver);
        monitor.start();
    }
}
