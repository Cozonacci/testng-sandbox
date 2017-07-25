package com.sandbox.training.csvreader;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.io.File.createTempFile;
import static java.io.File.separator;
import static java.lang.System.getProperty;
import static java.lang.System.lineSeparator;
import static java.util.Arrays.asList;

/**
 * Created by civanov on 24/07/17.
 */
public class CSVReader {

    private final Integer HEADER_ROW = 0;
    private final String CSV_SEPARATOR = ",";

    public Object[][] getTestData(String[] columns, String filePath) throws IOException {

        Map<String, List<String>> parameters = read(filePath);
        Object[][] testData = new Object[parameters.size()][parameters.keySet().size()];

        for (int i = 0; i < columns.length; i++) {
            for (int j = 0; j < parameters.size(); j++) {

                testData[i][j] = parameters.get(columns[j]).get(i).trim();
            }
        }
        return testData;
    }

    private Map<String, List<String>> read(String filePath) throws IOException {

        List<String> headers = new ArrayList<>();
        List<String> values;

        Map<String, List<String>> parameters = new HashMap<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader(getProperty("user.dir") + "\\" + filePath))) {
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




//        try (BufferedReader csvReader = new BufferedReader(new FileReader(getProperty("user.dir") + "\\" + filePath))) {
//
//            csvReader.lines().findFirst()
//                    .ifPresent(line -> headers.addAll(asList(line.split(","))));
//
//        }
//
//        try (BufferedReader csvReader = new BufferedReader(new FileReader(getProperty("user.dir") + "\\" + filePath))) {
//
//            values.add(csvReader.lines().filter(line -> !headers.equals(asList(line.split(",")))).toArray());
//
//        }
//
//        System.out.println(headers.toString());
//        System.out.println(values.toString());