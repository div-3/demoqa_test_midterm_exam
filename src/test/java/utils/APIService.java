package utils;

import io.restassured.response.Response;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class APIService {

    private final String PROPERTIES_FILE_PATH = "src/main/resources/api_service.properties";

    //Реализация Singleton по https://habr.com/ru/articles/27108/
    private APIService() {
    }

    public static APIService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private final static APIService instance = new APIService();
    }

    public Map<String, String> login() {
        AuthDataProvider authDataProvider = AuthDataProvider.getInstance();
        String userName = authDataProvider.getUserName();
        String password = authDataProvider.getPassword();
        if (userName == null || password == null) return null;

        return getToken(userName, password);
    }

    public void deleteAllBooks() {
        Map<String, String> authInfo = login();

        given()
                .baseUri(getProperties().getProperty("url.base") + getProperties().getProperty("url.delete_all"))
                .param("UserId", authInfo.get("userId"))
                .contentType("application/json")
                .header("Authorization", "Bearer " + authInfo.get("token"))
                .log().ifValidationFails()
                .delete()
                .then()
                .log().ifValidationFails()
                .statusCode(204);
    }

    private Map<String, String> getToken(String userName, String password) {

        Response response =
                given()
                        .baseUri(getProperties().getProperty("url.base") + getProperties().getProperty("url.login"))
                        .contentType("application/json; charset=utf-8")
                        .body("{\"userName\": \"" + userName + "\", \"password\": \"" + password + "\"}")
                        .when()
                        .post();

        Map<String, String> authInfo = new HashMap<>();
        response.then().log().ifValidationFails().statusCode(200);
        authInfo.put("userId", response.then().extract().path("userId").toString());
        authInfo.put("token", response.then().extract().path("token").toString());

        return authInfo;
    }

    //Получить параметры из файла
    public Properties getProperties() {
        File propFile = new File(PROPERTIES_FILE_PATH);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(propFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }


}
