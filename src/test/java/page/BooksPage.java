package page;

import block.BookRow;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class BooksPage extends Page {
    @FindBy(id = "searchBox")
    private WebElement serchInput;
    @FindBy(css = ".rt-tbody")
    private WebElement tableBody;
    @FindBy(css = ".rt-tbody>.rt-tr-group>.rt-tr:not(.-padRow)")
    private List<WebElement> tableRows;
    private ArrayList<BookRow> bookRows = new ArrayList<>();


    public BooksPage(WebDriver driver) {
        super(driver, Pages.BOOKS);
        PageFactory.initElements(driver, this);
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
        scrollDownPage(0, 500);
        if (bookRows.isEmpty()) getBooks();
        bookRows.get(row).openBook();
        return new CustomBookPage(driver);
    }
}
