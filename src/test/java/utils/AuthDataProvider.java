package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AuthDataProvider {

    private final static String AUTH_DATA_PATH = "src/main/resources/auth.properties";
    private final static String USER_PROP_NAME = "user";
    private final static String PASSWORD_PROP_NAME = "password";


    public static String getUserName(){
        return getAuthDataFromFile(USER_PROP_NAME);
    }

    public static String getPassword(){
        return getAuthDataFromFile(PASSWORD_PROP_NAME);
    }

    private static String getAuthDataFromFile(String page){
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
