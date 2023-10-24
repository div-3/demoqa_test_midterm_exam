package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AuthDataProvider {

    private final String AUTH_DATA_PATH = "src/main/resources/auth.properties";
    private final String USER_PROP_NAME = "user";
    private final String PASSWORD_PROP_NAME = "password";


    //Реализация Singleton по https://habr.com/ru/articles/27108/
    private AuthDataProvider() {}

    public static AuthDataProvider getInstance() {
        return AuthDataProvider.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private final static AuthDataProvider instance = new AuthDataProvider();
    }

    public String getUserName() {
        return getAuthDataFromFile(USER_PROP_NAME);
    }

    public String getPassword() {
        return getAuthDataFromFile(PASSWORD_PROP_NAME);
    }

    private String getAuthDataFromFile(String page) {
        File propFile = new File(AUTH_DATA_PATH);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(propFile));
            return properties.getProperty(page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
