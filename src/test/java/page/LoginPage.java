package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AuthDataProvider;

public class LoginPage extends Page {
    @FindBy(id = "userName")
    private WebElement userNameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "login")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver, Pages.LOGIN);
        PageFactory.initElements(driver, this);
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
