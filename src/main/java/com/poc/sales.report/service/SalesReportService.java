package com.poc.sales.report.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class SalesReportService {

    public void proccessFile(File file) {
        try {
            Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()));

            stream.forEach(line -> {
                ParseFileContentService.checkExpression(line, ParseFileContentService.REGEX_SALESMAN);

                System.out.println(ParseFileContentService.getResult().toMatchResult());
            });
        } catch (Exception ex) {

        }
    }
}
