package com.poc.sales.report.service;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Service
public final class DirectoryEventListenerService implements FileAlterationListener {

    private ValidateFileService validateFileService;
    private SalesReportService salesReportService;

    public DirectoryEventListenerService(ValidateFileService validateFileService, SalesReportService salesReportService) {
        this.validateFileService = validateFileService;
        this.salesReportService = salesReportService;
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
        File directory = observer.getDirectory();
        if (!directory.exists())
            System.out.println(directory.getAbsolutePath() + " The directory that will be monitored does not exist. Create the directory to be monitored");

        File[] files = directory.listFiles();
        Arrays.stream(files != null ? files : new File[0]).forEach(this::onFileCreate);
    }

    @Override
    public void onStop(final FileAlterationObserver observer) { }

    @Override
    public void onDirectoryCreate(File directory) { }

    @Override
    public void onDirectoryChange(final File directory) { }

    @Override
    public void onDirectoryDelete(final File directory) { }

    @Override
    public void onFileCreate(final File file) {
        if (validateFileService.validateFile(file)) {
            salesReportService.processInputFileData(file);
        }
    }

    @Override
    public void onFileChange(final File file) { }

    @Override
    public void onFileDelete(final File file) { }
}