package com.poc.sales.report.Service;

import com.poc.sales.report.config.FileExtensionsConfig;
import com.poc.sales.report.service.ValidateFileService;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Objects;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ValidateFileServiceTest {

    private ValidateFileService validateFileService;
    private FileExtensionsConfig fileExtensionsConfig;

    @Before
    public void setUp() {
        this.fileExtensionsConfig = new FileExtensionsConfig();
        this.validateFileService = new ValidateFileService(fileExtensionsConfig);
    }

    @Test
    public void shouldValidateFileWithSuccess() {
        fileExtensionsConfig.setExtensions(getFileExtensions());
        assertTrue(validateFileService.validateFile(getFileByName("sale.dat")));
    }

    @Test
    public void shouldValidateFileWithInvalidExtension() {
        fileExtensionsConfig.setExtensions(getFileExtensions());
        assertFalse(validateFileService.validateFile(getFileByName("invalid_extension.txt")));
    }

    private String[] getFileExtensions() {
        return new String[] { "dat" };
    }

    private File getFileByName(String fileName) {
        return new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile()
        );
    }
}
