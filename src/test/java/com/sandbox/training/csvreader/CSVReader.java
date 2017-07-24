package com.sandbox.training.csvreader;

import java.util.*;

/**
 * Created by civanov on 24/07/17.
 */
public class CSVReader {

    public static Object[][] getTestData(String[] columns, String filepath) {

        read();

        System.out.println(columns + " " + columns);

        return new Object[][]
                {
                        // h1,      h2,        h3
                        {"first", "second"} // removed
                };
    }



    private static Map<String, List<String>> read() {
        Map<String, List<String>> values = new HashMap<String, List<String>>();

        values.put("firstColumnHeader", Arrays.asList("11", "12"));
        values.put("secondColumnHeader", Arrays.asList("21", "22"));

        return values;
    }
}
