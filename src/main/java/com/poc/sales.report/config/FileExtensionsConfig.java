package com.poc.sales.report.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileExtensionsConfig {

    public String[] extensions;

    public void setExtensions(String[] extensions) {
        this.extensions = extensions;
    }
}
