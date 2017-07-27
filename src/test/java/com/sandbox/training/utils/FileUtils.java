package com.sandbox.training.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

public class FileUtils {

    private static final Logger logger = Logger.getLogger(FileUtils.class.getName());

    public static FileReader getFileFromResources(String filePath) {
        FileReader csvFile = null;
        try {
            csvFile = new FileReader(FileUtils.class.getResource(filePath).getPath());
        } catch (FileNotFoundException e) {
            logger.severe("No file found in resources with the file path " + filePath);
        }
        return csvFile;
    }
}