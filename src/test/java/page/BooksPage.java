package page;

import block.BookRow;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BooksPage extends Page {
    private SelenideElement serchInput = $(By.id("searchBox"));
    private SelenideElement tableBody = $(By.cssSelector(".rt-tbody"));
    private List<SelenideElement> tableRows = $$(By.cssSelector(".rt-tbody>.rt-tr-group>.rt-tr:not(.-padRow)"));
    private ArrayList<BookRow> bookRows = new ArrayList<>();


//    public BooksPage(WebDriver driver) {
    public BooksPage() {
        super(Pages.BOOKS);
//        PageFactory.initElements(driver, this);
    }

    @Step("Получить все книги на странице BOOKS")
    public ArrayList<BookRow> getBooks() {
        for (int i = 0; i < tableRows.size(); i++) {
            bookRows.add(new BookRow(tableRows.get(i)));
        }
        return bookRows;
    }

    @Step("Открыть книгу из строки '{row}'")
    public CustomBookPage openBookAtRow(int row) {
//        scrollDownPage(0, 500);
        if (bookRows.isEmpty()) getBooks();
        bookRows.get(row).openBook();
        return new CustomBookPage();
//        return new CustomBookPage(driver);
    }
}
