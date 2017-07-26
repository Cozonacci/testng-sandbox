package com.sandbox.training.testng;

import com.sandbox.training.annotations.CsvDataProvider;
import com.sandbox.training.csvreader.CSVReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class ReadFromExternalCsvTest {

    @Test(dataProvider = "ExternalCsvData", parameters = {"keyword", "username"})
    @CsvDataProvider(columns = {"keyword", "username"}, filePath = "/testdata/file.csv")
    public void outputTestDataFromCsv(String keyword, String username) {
        System.out.println(keyword + " " + username);
    }

    @Test(dataProvider = "ExternalCsvData", parameters = {"keyword", "username", "test", "accepted"})
    @CsvDataProvider(filePath = "/testdata/file.csv")
    public void outputTestDataFromCsvAllColumns(String keyword, String username, String test, boolean accepted) {
        System.out.println(keyword + " " + username + " " + test + " " + accepted);
    }

    @DataProvider(name = "ExternalCsvData")
    public static Object[][] testDataFromCsv(final Method csvDataProvider) throws IOException {
        CsvDataProvider parameters = csvDataProvider.getAnnotation(CsvDataProvider.class);
        String[] columns = parameters.columns();
        String filepath = parameters.filePath();

        return new CSVReader().getTestData(columns, filepath);
    }
}