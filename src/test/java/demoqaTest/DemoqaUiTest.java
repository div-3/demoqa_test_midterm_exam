package demoqaTest;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.LoginPage;
import page.ProfilePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class DemoqaUiTest {
    @Test
    @DisplayName("Сценарий 1")
    public void demoqaUiTest(FirefoxDriver browser) throws InterruptedException {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Открыть страницу https://demoqa.com/login
        LoginPage loginPage = new LoginPage(browser);
        loginPage.loadPage();

        //Ввести логин и пароль
        loginPage.auth();

        //Перейти в раздел https://demoqa.com/profile
        ProfilePage profilePage = new ProfilePage(browser);

        //Проверить, что таблица пустая
        assertTrue(profilePage.isBookTableEmpty());
    }


}
