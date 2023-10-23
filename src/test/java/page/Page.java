package page;

import block.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.UrlProvider;

import static com.codeborne.selenide.Selenide.open;

public class Page {
//    protected WebDriver driver;
    protected Pages currentPage;    //Тип страницы
    protected Header header;

//    public Page(WebDriver driver, Pages page) {
    public Page(Pages page) {
//        this.driver = driver;
        this.currentPage = page;
//        header = new Header(driver);
        header = new Header();
    }

    @Step("Загрузить страницу '{this.currentPage}'")
    public void loadPage() {
        open(UrlProvider.getUrlByPagesEnum(currentPage));
//        driver.get(UrlProvider.getUrlByPagesEnum(currentPage));
    }

    @Step("Прокрутка страницы на '{pixelX}' по горизонтали и '{pixelY}' по вертикали")
    protected void scrollDownPage(int pixelX, int pixelY) {

//        Actions actions = new Actions(driver);
//        actions.scrollByAmount(pixelX, pixelY).perform();
    }
}
