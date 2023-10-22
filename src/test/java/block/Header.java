package block;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private WebDriver driver;
    @FindBy(css = ".main-header")
    private WebElement headerText;
    @FindBy(id = "userName-value")
    private WebElement userName;
    @FindBy(id = "submit")
    private WebElement logoutButton;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Header awaitUserName(String name){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .withMessage("Не дождались авторизации!")
        .until(ExpectedConditions.textToBePresentInElement (userName, name));
        return this;
    }
}
