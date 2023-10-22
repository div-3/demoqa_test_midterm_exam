package demoqaTest;

import block.RowCount;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import page.CustomBookPage;
import page.BooksPage;
import page.LoginPage;
import page.ProfilePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class DemoqaUiTest {

    public void clearDataByAPI() {

    }

    @Test
    @DisplayName("Сценарий 1")
    public void demoqaUiTest(FirefoxDriver browser) {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Открыть страницу https://demoqa.com/login
        LoginPage loginPage = new LoginPage(browser);
        loginPage.loadPage();

        //Ввести логин и пароль
        loginPage.auth();

        //Перейти в раздел https://demoqa.com/profile
        ProfilePage profilePage = new ProfilePage(browser);

        //Проверить, что таблица пустая
        assertTrue(profilePage.isNoRowNotificationDisplayed());
    }

    @Test
    @DisplayName("Сценарий 2")
    public void demoqaUiTest2(FirefoxDriver browser) {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Открыть страницу https://demoqa.com/login
        LoginPage loginPage = new LoginPage(browser);
        loginPage.loadPage();

        //Ввести логин и пароль
        loginPage.auth();

        //Перейти в раздел https://demoqa.com/books
        BooksPage booksPage = new BooksPage(browser);
        booksPage.loadPage();

        //Добавить в коллекцию 6 книг
        for (int i = 0; i < 6; i++) {
            CustomBookPage book = booksPage.openBookAtRow(i);
            book.addToCollection();
            booksPage = book.backToStore();
        }

        //Перейти в раздел https://demoqa.com/profile
        ProfilePage profilePage = new ProfilePage(browser);
        profilePage.loadPage();
        profilePage.setBookRowPerPage(RowCount.ROWS_20);

        //Проверить, что в коллекции отображается 6 книг
        int booksCount = profilePage.getBooks().size();
        assertEquals(6, booksCount);
    }

    @Test
    @DisplayName("Сценарий 3")
    public void demoqaUiTest3(FirefoxDriver browser) throws InterruptedException {
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Открыть страницу https://demoqa.com/login
        LoginPage loginPage = new LoginPage(browser);
        loginPage.loadPage();

        //Ввести логин и пароль
        loginPage.auth();

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
        profilePage.setBookRowPerPage(RowCount.ROWS_20);

        //Проверить, что в коллекции отображается 2 книги
        int booksCount = profilePage.getBooks().size();
        assertEquals(2, booksCount);


    }
}
