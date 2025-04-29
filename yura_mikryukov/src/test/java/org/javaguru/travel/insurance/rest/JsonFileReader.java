package org.javaguru.travel.insurance.rest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileReader {

    public String readJsonFromFile(String filePath) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}