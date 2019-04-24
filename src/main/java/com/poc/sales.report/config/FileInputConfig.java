package com.poc.sales.report.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileInputConfig {

    @Value("${file.data.in.fileDirectoryPath}")
    private String fileDirectoryPath;

    @Value("${file.data.in.monitorInterval}")
    private Integer monitorInterval;

    public String getFileDirectoryPath() {
        return fileDirectoryPath;
    }

    public Integer getMonitorInterval() {
        return monitorInterval;
    }
}
