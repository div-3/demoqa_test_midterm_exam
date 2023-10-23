package demoqaTest;

import block.RowCount;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.BooksPage;
import page.CustomBookPage;
import page.LoginPage;
import page.ProfilePage;
import utils.APIService;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
//import static com.codeborne.selenide.WebDriverRunner.browser;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Профиль")
@ExtendWith({SeleniumJupiter.class})
@DisplayName("UI-тесты demoqa.com:")
public class DemoqaUiTest{

    @BeforeAll
    public static void setUp(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
    }

    //Очистка данных через API
    @AfterEach
    public void clearDataAPI() {
        APIService apiService = new APIService();
        apiService.deleteAllBooks();
    }

    @Test
    @DisplayName("Сценарий 1")
    @Description("Тест авторизации и проверки пустого профиля.")
    @Story("Как пользователь, я могу авторизоваться и проверить пустой профиль")
    @Feature("Авторизация")
    @Tags({@Tag("Authorization"), @Tag("Profile")})  //Теги для JUnit и Allure
    @Severity(SeverityLevel.BLOCKER)    //Важность теста для Allure
    @Owner("Dudorov")
    @Tag("Positive")
//    public void demoqaUiTest(FirefoxDriver browser) {
    public void demoqaUiTest() {
        open();
//        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LoginPage loginPage = new LoginPage();
        ;
        step("1. Открыть страницу https://demoqa.com/login", () -> {
            loginPage.loadPage();
        });

        step("2. Ввести логин и пароль", () -> {
            loginPage.auth();
        });

        step("3. Перейти в раздел https://demoqa.com/profile");
        ProfilePage profilePage = new ProfilePage();

        step("4. Проверить, что таблица пустая", () -> {
            assertTrue(profilePage.isNoRowNotificationDisplayed());
            assertFalse(profilePage.isNoRowNotificationDisplayed());
        });
    }

    @Test
    @DisplayName("Сценарий 2")
    @Description("Тест добавления 6 книг в профиль.")
    @Story("Как пользователь, я могу авторизоваться и добавлять книги в профиль")
    @Feature("Добавление книг в профиль")
    @Tags({@Tag("Authorization"), @Tag("Profile"), @Tag("BookStore")})  //Теги для JUnit и Allure
    @Severity(SeverityLevel.BLOCKER)    //Важность теста для Allure
    @Owner("Dudorov")
    @Tag("Positive")
//    public void demoqaUiTest2(FirefoxDriver browser) {
    public void demoqaUiTest2() {
//        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        open();
        LoginPage loginPage = new LoginPage();
        step("1. Открыть страницу https://demoqa.com/login", () -> {
            loginPage.loadPage();
        });

        step("2. Ввести логин и пароль", () -> {
            loginPage.auth();
        });

        step("3. Перейти в раздел https://demoqa.com/books");
        BooksPage booksPage = new BooksPage();
        booksPage.loadPage();

        step("4. Добавить в коллекцию 6 книг");
        for (int i = 0; i < 6; i++) {
            CustomBookPage book = booksPage.openBookAtRow(i);
            book.addToCollection();
            booksPage = book.backToStore();
        }


        ProfilePage profilePage = new ProfilePage();
        step("5. Перейти в раздел https://demoqa.com/profile", () -> {
            profilePage.loadPage();
            profilePage.setBookRowPerPage(RowCount.ROWS_20);
        });

        step("6. Проверить, что в коллекции отображается 6 книг", () -> {
            assertEquals(6, profilePage.getBooks().size());
        });
    }

    @Test
    @DisplayName("Сценарий 3")
    @Description("Тест добавления книг в профиль и удаления всех книг из профиля.")
    @Story("Как пользователь, я могу авторизоваться, добавлять и удалять книги в профиль")
    @Feature("Удаление книг из профиля")
    @Tags({@Tag("Authorization"), @Tag("Profile"), @Tag("BookStore")})  //Теги для JUnit и Allure
    @Severity(SeverityLevel.BLOCKER)    //Важность теста для Allure
    @Owner("Dudorov")
    @Tag("Positive")
//    public void demoqaUiTest3(FirefoxDriver browser) throws InterruptedException {
    public void demoqaUiTest3() throws InterruptedException {
//        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        open();
        LoginPage loginPage = new LoginPage();
        ;
        step("1. Открыть страницу https://demoqa.com/login", () -> {
            loginPage.loadPage();
        });

        step("2. Ввести логин и пароль", () -> {
            loginPage.auth();
        });

        step("3. Перейти в раздел https://demoqa.com/books");
        BooksPage booksPage = new BooksPage();
        booksPage.loadPage();

        step("4. Добавить в коллекцию 2 книги");
        for (int i = 0; i < 2; i++) {
            CustomBookPage book = booksPage.openBookAtRow(i);
            book.addToCollection();
            booksPage = book.backToStore();
        }

        ProfilePage profilePage = new ProfilePage();
        step("5. Перейти в раздел https://demoqa.com/profile", () -> {
            profilePage.loadPage();
        });

        step("6. Проверить, что в коллекции отображается 2 книги", () -> {
            assertEquals(2, profilePage.getBooks().size());
        });

        step("7. Нажать Delete All Books", () -> {
            profilePage.deleteAllBooks();
        });

        step("8. Проверить, что таблица пустая", () -> {
            assertTrue(profilePage.isNoRowNotificationDisplayed());
        });
    }
}
