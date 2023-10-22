package page;

import block.BookRow;
import block.RowCount;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends Page {
    @FindBy(id = "searchBox")
    private WebElement bookSearchInput;
    @FindBy(css = ".rt-tbody")
    private WebElement tableBody;
    @FindBy(css = ".rt-noData")
    private WebElement noRowsNotification;
    @FindBy(css = "select[aria-label='rows per page']")
    private WebElement selectRowPerPage;

    @FindBy(css = ".rt-tbody>.rt-tr-group>.rt-tr:not(.-padRow)")
    private List<WebElement> tableRows;
    private ArrayList<BookRow> bookRows = new ArrayList<>();

    public ProfilePage(WebDriver driver) {
        super(driver, Pages.PROFILE);
        PageFactory.initElements(driver, this);
    }

    public ArrayList<BookRow> getBooks() {
        System.out.println("Количество строк в таблице: " + tableRows.size());
        for (int i = 0; i < tableRows.size(); i++) {
            bookRows.add(new BookRow(tableRows.get(i)));
        }
        return bookRows;
    }

    public void setBookRowPerPage(RowCount count){
        scrollDownPage(0, 500);
        Select select = new Select(selectRowPerPage);
        select.selectByValue(count.getCode());
    }

    public boolean isNoRowNotificationDisplayed() {
        return noRowsNotification.isDisplayed();
    }
}
