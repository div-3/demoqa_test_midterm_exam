package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomBookPage extends Page {

    @FindBy(xpath = "//*[text()='Back To Book Store']")
    private WebElement backToStoreButton;
    @FindBy(xpath = "//*[text()='Add To Your Collection']")
    private WebElement addToCollectionButton;


    public CustomBookPage(WebDriver driver) {
        super(driver, Pages.BOOKS);
        PageFactory.initElements(driver, this);
    }

    public void addToCollection() {
        scrollDownPage(0, 500);
        addToCollectionButton.click();

        //Ждём появления Alert
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .withMessage("Не дождались Alert")
                .until(ExpectedConditions.alertIsPresent());

        //Подтверждаем Alert
        driver.switchTo().alert().accept();
    }

    public BooksPage backToStore() {
        scrollDownPage(0, 500);
        backToStoreButton.click();
        return new BooksPage(driver);
    }
}
