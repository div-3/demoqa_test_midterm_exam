package demoqaTest;

import block.RowCount;
import io.github.bonigarcia.seljup.Arguments;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.BooksPage;
import page.CustomBookPage;
import page.LoginPage;
import page.ProfilePage;
import utils.APIService;

import java.time.Duration;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Профиль")
@ExtendWith(SeleniumJupiter.class)
@DisplayName("UI-тесты demoqa.com:")
public class DemoqaUiTest {

    //Очистка данных через API
    @AfterEach
    public void clearDataAPI() {
        APIService apiService = new APIService();
        apiService.deleteAllBooks();
    }

    @Test
    @DisplayName("Сценарий 1")
    @Description("Тест авторизации и проверки пустого профиля.")
    @Story("Как пользователь, я могу авторизовываться и проверить пустой профиль")
    @Feature("Авторизация")
    @Tags({@Tag("Authorization"), @Tag("Profile")})  //Теги для JUnit и Allure
    @Severity(SeverityLevel.BLOCKER)    //Важность теста для Allure
    @Owner("Dudorov")
    @Tag("Positive")
    public void demoqaUiTest(FirefoxDriver browser) {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LoginPage loginPage = new LoginPage(browser);
        ;
        step("1. Открыть страницу https://demoqa.com/login", () -> {
            loginPage.loadPage();
        });

        step("2. Ввести логин и пароль", () -> {
            loginPage.auth();
        });

        step("3. Перейти в раздел https://demoqa.com/profile");
        ProfilePage profilePage = new ProfilePage(browser);

        step("4. Проверить, что таблица пустая", () -> {
            assertTrue(profilePage.isNoRowNotificationDisplayed());
        });
    }

    @Test
    @DisplayName("Сценарий 2")
    @Description("Тест добавления 6 книг в профиль.")
    @Story("Как пользователь, я могу авторизовываться и добавлять книги в профиль")
    @Feature("Добавление книг в профиль")
    @Tags({@Tag("Authorization"), @Tag("Profile"), @Tag("BookStore")})  //Теги для JUnit и Allure
    @Severity(SeverityLevel.BLOCKER)    //Важность теста для Allure
    @Owner("Dudorov")
    @Tag("Positive")
    public void demoqaUiTest2(FirefoxDriver browser) {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LoginPage loginPage = new LoginPage(browser);
        ;
        step("1. Открыть страницу https://demoqa.com/login", () -> {
            loginPage.loadPage();
        });

        step("2. Ввести логин и пароль", () -> {
            loginPage.auth();
        });

        step("3. Перейти в раздел https://demoqa.com/books");
        BooksPage booksPage = new BooksPage(browser);
        booksPage.loadPage();

        for (int i = 0; i < 6; i++) {
            CustomBookPage book = booksPage.openBookAtRow(i);

            step("4. Добавить в коллекцию 6 книг", () -> {
                book.addToCollection();
            });
            booksPage = book.backToStore();
        }


        //
        ProfilePage profilePage = new ProfilePage(browser);
        step("5. Перейти в раздел https://demoqa.com/profile", () -> {
            profilePage.loadPage();
            profilePage.setBookRowPerPage(RowCount.ROWS_20);
        });

        //
        step("6. Проверить, что в коллекции отображается 6 книг", () -> {
            int booksCount = profilePage.getBooks().size();
            assertEquals(6, booksCount);
        });
    }

    @Test
    @DisplayName("Сценарий 3")
    @Description("Тест добавления книг в профиль и удавления всех книг из профиля.")
    @Story("Как пользователь, я могу авторизовываться, добавлять и удалять книги в профиль")
    @Feature("Удаление книг из профиля")
    @Tags({@Tag("Authorization"), @Tag("Profile"), @Tag("BookStore")})  //Теги для JUnit и Allure
    @Severity(SeverityLevel.BLOCKER)    //Важность теста для Allure
    @Owner("Dudorov")
    @Tag("Positive")
    public void demoqaUiTest3(FirefoxDriver browser) throws InterruptedException {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LoginPage loginPage = new LoginPage(browser);
        ;
        step("1. Открыть страницу https://demoqa.com/login", () -> {
            loginPage.loadPage();
        });

        step("2. Ввести логин и пароль", () -> {
            loginPage.auth();
        });

        //Перейти в раздел https://demoqa.com/books
        BooksPage booksPage = new BooksPage(browser);
        booksPage.loadPage();

        //Добавить в коллекцию 2 книги
        for (int i = 0; i < 2; i++) {
            CustomBookPage book = booksPage.openBookAtRow(i);
            book.addToCollection();
            booksPage = book.backToStore();
        }

        //Перейти в раздел https://demoqa.com/profile
        ProfilePage profilePage = new ProfilePage(browser);
        profilePage.loadPage();

        //Проверить, что в коллекции отображается 2 книги
        int booksCount = profilePage.getBooks().size();
        assertEquals(2, booksCount);

        //Нажать Delete All Books
        profilePage.deleteAllBooks();

        //Проверить, что таблица пустая
        assertTrue(profilePage.isNoRowNotificationDisplayed());
    }


}
