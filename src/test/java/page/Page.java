package page;

import block.Header;
import io.qameta.allure.Step;
import utils.UrlProvider;

import static com.codeborne.selenide.Selenide.open;

public class Page {
    protected Pages currentPage;    //Тип страницы
    protected Header header;

    public Page(Pages page) {
        this.currentPage = page;
        header = new Header();
    }

    @Step("Загрузить страницу '{this.currentPage}'")
    public void loadPage() {
        UrlProvider urlProvider = UrlProvider.getInstance();
        open(urlProvider.getUrlByPagesEnum(currentPage));
    }
}
