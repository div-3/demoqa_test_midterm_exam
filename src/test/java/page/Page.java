package page;

import org.openqa.selenium.WebDriver;
import utils.UrlProvider;

public class Page {
protected WebDriver driver;
protected Pages currentPage;    //Тип страницы

    public Page(WebDriver driver, Pages page) {
        this.driver = driver;
        this.currentPage = page;
    }

    public void loadPage(){
        driver.get(UrlProvider.getUrlByPagesEnum(currentPage));
    }
}
