package page;

import block.BookRow;
import block.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
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
    public ArrayList<BookRow> getBooks() {
        System.out.println("Количество строк в таблице: " + tableRows.size());
        for (int i = 0; i < tableRows.size(); i++) {
            bookRows.add(new BookRow(tableRows.get(i)));
        }
        return bookRows;
    }

    public CustomBookPage openBookAtRow(int row) {
        scrollDownPage(0, 500);
        if (bookRows.isEmpty()) getBooks();
        bookRows.get(row).openBook();
        return new CustomBookPage(driver);
    }


}
