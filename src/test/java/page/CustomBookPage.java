package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class CustomBookPage extends Page {
//    @FindBy(xpath = "//*[text()='Back To Book Store']")
    private SelenideElement backToStoreButton = $(By.xpath("//*[text()='Back To Book Store']"));
//    @FindBy(xpath = "//*[text()='Add To Your Collection']")
    private SelenideElement addToCollectionButton = $(By.xpath("//*[text()='Add To Your Collection']"));


//    public CustomBookPage(WebDriver driver) {
    public CustomBookPage() {
//        super(driver, Pages.BOOKS);
        super(Pages.BOOKS);
//        PageFactory.initElements(driver, this);
    }

    @Step("Добавить книгу в коллекцию")
    public void addToCollection() {
//        scrollDownPage(0, 500);
        addToCollectionButton.scrollTo().click();

        //Ждём появления Alert
//        new WebDriverWait(driver, Duration.ofSeconds(5))
//                .withMessage("Не дождались Alert")
//                .until(ExpectedConditions.alertIsPresent());


        //Подтверждаем Alert
        Selenide.switchTo().alert().accept();
//        driver.switchTo().alert().accept();
    }

    @Step("Возврат на страницу BOOK STORE")
    public BooksPage backToStore() {
        scrollDownPage(0, 500);
        backToStoreButton.scrollTo().click();
//        return new BooksPage(driver);
        return new BooksPage();
    }
}
