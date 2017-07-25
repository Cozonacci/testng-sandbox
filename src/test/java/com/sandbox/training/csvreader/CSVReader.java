package com.sandbox.training.csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.getProperty;
import static java.util.Arrays.asList;

/**
 * Created by civanov on 24/07/17.
 */
public class CSVReader {

    private final int HEADER_ROW = 0;
    private final String CSV_SEPARATOR = ",";

    public Object[][] getTestData(String[] columns, String filePath) throws IOException {

        Map<String, List<String>> csvParameters = read(filePath);
        int nrOfRows = csvParameters.get(columns[0]).size();

        Object[][] testData = new Object[nrOfRows][columns.length];

        for (int i = 0; i < columns.length; i++) {
            String column = columns[i];

            for (int j = 0; j < csvParameters.get(columns[0]).size(); j++) {
                String csvColumn = csvParameters.get(column).get(j);
                if (csvColumn != null) {
                    testData[j][i] = csvParameters.get(column).get(j).trim();
                }
            }
        }
        return testData;
    }

    private Map<String, List<String>> read(String filePath) throws IOException {
        FileReader csvFileReader = new FileReader(getProperty("user.dir") + filePath);
        Map<String, List<String>> parameters = new HashMap<>();
        List<String> headers = new ArrayList<>();
        List<String> values;

        try (BufferedReader csvReader = new BufferedReader(csvFileReader)) {
            int row = 0;
            String line;

            while ((line = csvReader.readLine()) != null) {
                if (row == HEADER_ROW) {
                    headers = asList(line.split(CSV_SEPARATOR));
                    for (int i = 0; i < headers.size(); i++) {
                        parameters.put(headers.get(i), new ArrayList<>());
                    }
                } else {
                    values = asList(line.split(CSV_SEPARATOR));
                    for (int i = 0; i < values.size(); i++) {
                        parameters.put(headers.get(i), parameters.get(headers.get(i))).add(values.get(i));
                    }
                }
                row++;
            }
        }
        return parameters;
    }
}