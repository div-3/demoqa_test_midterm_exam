package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends Page{
    @FindBy(id = "searchBox")
    private WebElement bookSearchInput;
    @FindBy(css = ".rt-tbody")
    private WebElement tableBody;
    @FindBy(css = ".rt-noData")
    private WebElement noRowsNotification;

    public ProfilePage(WebDriver driver) {
        super(driver, Pages.PROFILE);
        PageFactory.initElements(driver, this);
    }

    public boolean isBookTableEmpty(){
        return noRowsNotification.isDisplayed();
    }
}
