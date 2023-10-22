package page;

import block.BookRow;
import block.RowCount;
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

public class ProfilePage extends Page {
    @FindBy(id = "searchBox")
    private WebElement bookSearchInput;
    @FindBy(css = ".rt-tbody")
    private WebElement tableBody;
    @FindBy(css = ".rt-noData")
    private WebElement noRowsNotification;
    @FindBy(xpath = "//*[text()='Delete All Books']")
    private WebElement deleteAllBooksButton;
    @FindBy(id = "closeSmallModal-ok")
    private WebElement modalOkButton;
    @FindBy(id = "closeSmallModal-cancel")
    private WebElement modalCancelButton;
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

    public void setBookRowPerPage(RowCount count) {
        scrollDownPage(0, 500);
        Select select = new Select(selectRowPerPage);
        select.selectByValue(count.getCode());
    }

    public boolean isNoRowNotificationDisplayed() {
        return noRowsNotification.isDisplayed();
    }

    public void deleteAllBooks() {
        scrollDownPage(0, 500);

        deleteAllBooksButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .withMessage("Не дождались модального окна с подтверждением!")
                .until(ExpectedConditions.elementToBeClickable(modalOkButton));

        modalOkButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .withMessage("Не дождались Alert")
                .until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();
    }
}
