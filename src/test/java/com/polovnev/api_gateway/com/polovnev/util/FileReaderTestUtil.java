package com.polovnev.api_gateway.com.polovnev.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderTestUtil {

    private static final String JSON_REQUEST_PATH_PREFIX = "src/test/resources/json/request/";
    private static final String JSON_RESPONSE_PATH_PREFIX = "src/test/resources/json/response/";
    private static final String JSON_EXTANSION = ".json";

    public static String getRequestJson(String fileName) throws IOException {
        return readTestFile(JSON_REQUEST_PATH_PREFIX + fileName);
    }

    public static String getResponseJson(String fileName) throws IOException {
        return readTestFile(JSON_RESPONSE_PATH_PREFIX + fileName);
    }

    private static String readTestFile(String pathString) throws IOException {
        Path path = Paths.get(pathString + JSON_EXTANSION);
        return String.join("\n", Files.readAllLines(path));
    }

}
