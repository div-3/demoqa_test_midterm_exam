package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CustomBookPage extends Page {
    private SelenideElement backToStoreButton = $(By.xpath("//*[text()='Back To Book Store']"));
    private SelenideElement addToCollectionButton = $(By.xpath("//*[text()='Add To Your Collection']"));

    public CustomBookPage() {
        super(Pages.BOOKS);
    }

    @Step("Добавить книгу в коллекцию")
    public void addToCollection() {
        addToCollectionButton.scrollTo().click();

        //Подтверждаем Alert
        Selenide.switchTo().alert().accept();
    }

    @Step("Возврат на страницу BOOK STORE")
    public BooksPage backToStore() {
        backToStoreButton.scrollTo().click();
        return new BooksPage();
    }
}
