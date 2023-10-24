package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.AuthDataProvider;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends Page {
    private SelenideElement userNameInput = $(By.id("userName"));
    private SelenideElement passwordInput = $(By.id("password"));
    private SelenideElement loginButton = $(By.id("login"));


    public LoginPage() {
        super(Pages.LOGIN);
    }

    @Step("Авторизация")
    public void auth() {
        AuthDataProvider authDataProvider = AuthDataProvider.getInstance();
        String name = authDataProvider.getUserName();
        userNameInput.sendKeys(name);
        passwordInput.sendKeys(authDataProvider.getPassword());
        loginButton.click();
        header.awaitUserName(name);
    }
}
