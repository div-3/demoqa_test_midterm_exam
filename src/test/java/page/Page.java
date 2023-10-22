package page;

import block.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.UrlProvider;

public class Page {
protected WebDriver driver;
protected Pages currentPage;    //Тип страницы
protected Header header;

    public Page(WebDriver driver, Pages page) {
        this.driver = driver;
        this.currentPage = page;
        header = new Header(driver);
    }

    public void loadPage(){
        driver.get(UrlProvider.getUrlByPagesEnum(currentPage));
    }

    protected void scrollDownPage(int pixelX, int pixelY){
        Actions actions = new Actions(driver);
        actions.scrollByAmount(pixelX, pixelY).perform();
    }
}
