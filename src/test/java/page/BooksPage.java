package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BooksPage extends Page{
    public BooksPage(WebDriver driver) {
        super(driver, Pages.BOOKS);
        PageFactory.initElements(driver, this);
    }


}
