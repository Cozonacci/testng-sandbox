package com.sandbox.training.csvreader;

import com.sandbox.training.utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

    private CSVFile csvFile = new CSVFile();
    private Map<String, List<String>> parameters = new LinkedHashMap<>();

    public Object[][] getTestData(String[] columns, String filePath) throws IOException {

        Map<String, List<String>> csvParameters = read(filePath);

        // if no column to be filtered out return all existing columns values
        if (columns == null || columns.length == 0)
            columns = csvParameters.keySet().toArray(new String[csvParameters.keySet().size()]);

        // equals first list size
        int nrOfDataRows = csvParameters.values().stream().findFirst().get().size();

        Object[][] testData = new Object[nrOfDataRows][columns.length];

        for (int i = 0; i < columns.length; i++) {
            String column = columns[i];

            for (int j = 0; j < csvParameters.get(columns[0]).size(); j++) {
                String csvColumn = csvParameters.get(column).get(j);

                if (csvColumn != null) {
                    String value = csvParameters.get(column).get(j).trim();

                    if (value.startsWith("(bool)")) {
                        testData[j][i] = Boolean.valueOf(value.substring(6));
                    } else if (value.startsWith("(date)")) {
                        testData[j][i] = LocalDateTime.parse(value.substring(6));
                    } else {
                        testData[j][i] = value;
                    }
                }
            }
        }
        return testData;
    }

    private Map<String, List<String>> read(String filePath) throws IOException {

        FileReader csvFileReader = FileUtils.getFileFromResources(filePath);

        try (BufferedReader csvReader = new BufferedReader(csvFileReader)) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                csvFile.read(line, parameters);
            }
        }
        return parameters;
    }
}