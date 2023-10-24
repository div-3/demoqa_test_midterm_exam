package utils;

import page.Pages;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UrlProvider {
    private final String URL_DATA_PATH = "src/main/resources/urls.properties";
    private final String BASE = "url.";

    //Реализация Singleton по https://habr.com/ru/articles/27108/
    private UrlProvider() {
    }

    public static UrlProvider getInstance() {
        return UrlProvider.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private final static UrlProvider instance = new UrlProvider();
    }


    public String getUrlByPagesEnum(Pages page) {
        return getUrlFromFile(BASE + page.toString());
    }

    private String getUrlFromFile(String page) {
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
