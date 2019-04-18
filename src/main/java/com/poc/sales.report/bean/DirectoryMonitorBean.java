package com.poc.sales.report.bean;

import com.poc.sales.report.service.DirectoryEventListenerService;
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
    private static final String FILES_DIRECTORY_PATH = "/data/in";
    private static final Integer MONITOR_INTERVAL = 10000;

    private ValidateFileService validateFileService;
    private SalesReportService salesReportService;

    public DirectoryMonitorBean(ValidateFileService validateFileService, SalesReportService salesReportService) {
        this.validateFileService = validateFileService;
        this.salesReportService = salesReportService;
    }

    @Bean
    public void initializeDirectoryMonitor() throws Exception{
        final File directory = new File(BASE_DIRECTORY_PATH.concat(FILES_DIRECTORY_PATH));

        FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(directory);
        fileAlterationObserver.addListener(new DirectoryEventListenerService(validateFileService, salesReportService));

        final FileAlterationMonitor monitor = new FileAlterationMonitor(MONITOR_INTERVAL);
        monitor.addObserver(fileAlterationObserver);
        monitor.start();
    }
}
