package block;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class Header {
//    private WebDriver driver;
    private SelenideElement headerText = $(By.cssSelector(".main-header"));
    private SelenideElement userName = $(By.id("userName-value"));
    private SelenideElement logoutButton = $(By.id("submit"));

//    public Header(WebDriver driver) {
    public Header() {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }

    public Header awaitUserName(String name) {
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .withMessage("Не дождались авторизации!")
//                .until(ExpectedConditions.textToBePresentInElement(userName, name));
        userName.should(Condition.text(name));
        return this;
    }
}
