package page;

import block.BookRow;
import block.RowCount;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage extends Page {
//    @FindBy(id = "searchBox")
    private SelenideElement bookSearchInput = $(By.id("searchBox"));
//    @FindBy(css = ".rt-tbody")
    private SelenideElement tableBody = $(By.cssSelector(".rt-tbody"));
//    @FindBy(css = ".rt-noData")
    private SelenideElement noRowsNotification = $(By.cssSelector(".rt-noData"));
//    @FindBy(xpath = "//*[text()='Delete All Books']")
    private SelenideElement deleteAllBooksButton = $(By.xpath("//*[text()='Delete All Books']"));
//    @FindBy(id = "closeSmallModal-ok")
    private SelenideElement modalOkButton = $(By.id("closeSmallModal-ok"));
//    @FindBy(id = "closeSmallModal-cancel")
    private SelenideElement modalCancelButton = $(By.id("closeSmallModal-cancel"));
//    @FindBy(css = "select[aria-label='rows per page']")
    private SelenideElement selectRowPerPage = $(By.cssSelector("select[aria-label='rows per page']"));
//    @FindBy(css = ".rt-tbody>.rt-tr-group>.rt-tr:not(.-padRow)")
    private List<SelenideElement> tableRows = $$(By.cssSelector(".rt-tbody>.rt-tr-group>.rt-tr:not(.-padRow)"));
    private ArrayList<BookRow> bookRows = new ArrayList<>();

//    public ProfilePage(WebDriver driver) {
    public ProfilePage() {
        super(Pages.PROFILE);
//        PageFactory.initElements(driver, this);
    }

    @Step("Получить список книг на странице PROFILE")
    public ArrayList<BookRow> getBooks() {
        for (int i = 0; i < tableRows.size(); i++) {
            bookRows.add(new BookRow(tableRows.get(i)));
        }
        return bookRows;
    }

    @Step("Изменить количество отображаемых в таблице книг на '{count.title}'")
    public void setBookRowPerPage(RowCount count) {
//        scrollDownPage(0, 500);
        selectRowPerPage.scrollTo().selectOptionByValue(count.getTitle());
//        Select select = new Select(selectRowPerPage);
//        select.selectByValue(count.getTitle());
    }

    @Step("Проверка, что в PROFILE отсутствуют книги")
    public boolean isNoRowNotificationDisplayed() {
        return noRowsNotification.isDisplayed();
    }

    @Step("Удалить все книги из PROFILE")
    public void deleteAllBooks() {
//        scrollDownPage(0, 500);

        deleteAllBooksButton.scrollTo().click();

//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .withMessage("Не дождались модального окна с подтверждением!")
//                .until(ExpectedConditions.elementToBeClickable(modalOkButton));

        modalOkButton.should(Condition.exist).click();

//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .withMessage("Не дождались Alert")
//                .until(ExpectedConditions.alertIsPresent());

        Selenide.switchTo().alert().accept();
//        driver.switchTo().alert().accept();
    }
}
