package page;

import block.BookRow;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BooksPage extends Page {
    private SelenideElement serchInput = $(By.id("searchBox"));
    private SelenideElement tableBody = $(By.cssSelector(".rt-tbody"));
    private List<SelenideElement> tableRows = $$(By.cssSelector(".rt-tbody>.rt-tr-group>.rt-tr:not(.-padRow)"));
    private ArrayList<BookRow> bookRows = new ArrayList<>();


    public BooksPage() {
        super(Pages.BOOKS);
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
        if (bookRows.isEmpty()) getBooks();
        bookRows.get(row).openBook();
        return new CustomBookPage();
    }
}
