package com.sandbox.training.testng;

import com.sandbox.training.annotations.CsvDataProvider;
import com.sandbox.training.csvreader.CSVReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * Created by dchicu on 7/23/2017.
 */
public class ReadFromExternalCsvTest {

    @Test(dataProvider = "ExternalCsvData", parameters = {"keyword", "username"})
    @CsvDataProvider(columns = {"keyword", "username"}, filepath = "testdata/file.csv")
    public void outputTestDataFromCsv(String keyword, String username) {
        System.out.println(keyword + " " + username);
    }

    @DataProvider(name = "ExternalCsvData")
    public static Object[][] testDataFromCsv(final Method csvDataProvider) {
        CsvDataProvider parameters = csvDataProvider.getAnnotation(CsvDataProvider.class);
        String[] columns = parameters.columns();
        String filepath = parameters.filepath();

        return CSVReader.getTestData(columns, filepath);
    }

}
