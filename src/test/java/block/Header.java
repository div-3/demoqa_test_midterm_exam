package block;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Header {
    private SelenideElement headerText = $(By.cssSelector(".main-header"));
    private SelenideElement userName = $(By.id("userName-value"));
    private SelenideElement logoutButton = $(By.id("submit"));

    public Header awaitUserName(String name) {
        userName.should(Condition.text(name));
        return this;
    }
}
