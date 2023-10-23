package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AuthDataProvider;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends Page {
//    @FindBy(id = "userName")
    private SelenideElement userNameInput = $(By.id("userName"));
//    @FindBy(id = "password")
    private SelenideElement passwordInput = $(By.id("password"));
//    @FindBy(id = "login")
    private SelenideElement loginButton = $(By.id("login"));


//    public LoginPage(WebDriver driver) {
    public LoginPage() {
        super(Pages.LOGIN);
//        PageFactory.initElements(driver, this);
    }

    @Step("Авторизация")
    public void auth() {
        String name = AuthDataProvider.getUserName();
        userNameInput.sendKeys(name);
        passwordInput.sendKeys(AuthDataProvider.getPassword());
        loginButton.click();
        header.awaitUserName(name);
    }
}
