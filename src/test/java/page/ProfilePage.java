package page;

import block.BookRow;
import block.RowCount;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage extends Page {
    private SelenideElement bookSearchInput = $(By.id("searchBox"));
    private SelenideElement tableBody = $(By.cssSelector(".rt-tbody"));
    private SelenideElement noRowsNotification = $(By.cssSelector(".rt-noData"));
    private SelenideElement deleteAllBooksButton = $(By.xpath("//*[text()='Delete All Books']"));
    private SelenideElement modalOkButton = $(By.id("closeSmallModal-ok"));
    private SelenideElement modalCancelButton = $(By.id("closeSmallModal-cancel"));
    private SelenideElement selectRowPerPage = $(By.cssSelector("select[aria-label='rows per page']"));
    private List<SelenideElement> tableRows = $$(By.cssSelector(".rt-tbody>.rt-tr-group>.rt-tr:not(.-padRow)"));
    private ArrayList<BookRow> bookRows = new ArrayList<>();

    public ProfilePage() {
        super(Pages.PROFILE);
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
        selectRowPerPage.scrollTo().selectOptionByValue(count.getTitle());
    }

    @Step("Проверка, что в PROFILE отсутствуют книги")
    public boolean isNoRowNotificationDisplayed() {
        return noRowsNotification.isDisplayed();
    }

    @Step("Удалить все книги из PROFILE")
    public void deleteAllBooks() {
        deleteAllBooksButton.scrollTo().click();
        modalOkButton.should(Condition.exist).click();
        Selenide.switchTo().alert().accept();
    }
}
