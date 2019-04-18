package com.poc.sales.report.service;

import com.poc.sales.report.config.FileExtensionsConfig;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Service
public class ValidateFileService {

    private FileExtensionsConfig fileExtensionsConfig;

    public ValidateFileService(FileExtensionsConfig fileExtensionsConfig) {
        this.fileExtensionsConfig = fileExtensionsConfig;
    }

    public boolean validateFile(File file) {
        if (!isFileExtensionAcceptable(file)) {
            System.out.println(file.getAbsoluteFile() + " have a invalid extension.");
            return false;
        }

        if (!isFileReadable(file)){
            System.out.println(file.getAbsoluteFile() + " isn't readable.");
            return false;
        }
        return true;
    }

    private boolean isFileExtensionAcceptable(File file) {
        return Arrays.stream(fileExtensionsConfig.extensions)
                .anyMatch(
                        extension -> extension.equals(FilenameUtils.getExtension(file.getName()))
                );
    }

    private boolean isFileReadable(File file) {
        return file.canRead();
    }
}
