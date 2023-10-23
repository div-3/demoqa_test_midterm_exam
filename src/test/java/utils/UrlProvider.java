package utils;

import page.Pages;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UrlProvider {
    private final static String URL_DATA_PATH = "src/main/resources/urls.properties";
    private final static String BASE = "url.";

    public static String getUrlByPagesEnum(Pages page) {
        return getUrlFromFile(BASE + page.toString());
    }

    private static String getUrlFromFile(String page) {
        File propFile = new File(URL_DATA_PATH);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(propFile));
            return properties.getProperty(page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
