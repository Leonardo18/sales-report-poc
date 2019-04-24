package com.poc.sales.report.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileOutputConfig {

    @Value("${file.data.out.fileDirectoryPath}")
    private String fileDirectoryPath;

    @Value("${file.data.out.extension}")
    private String extension;

    public String getFileDirectoryPath() {
        return fileDirectoryPath;
    }

    public String getExtension() {
        return extension;
    }
}
