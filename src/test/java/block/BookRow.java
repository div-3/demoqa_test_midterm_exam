package block;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookRow {
    @FindBy(css = "img")
    private WebElement bookImage;
    @FindBy(css = "a")
    private WebElement bookTitleLink;
    @FindBy(css = "div:nth-child(3)")
    private WebElement bookAuthor;
    @FindBy(css = "div:nth-child(4)")
    private WebElement bookPublisher;

    public BookRow(WebElement element) {
        PageFactory.initElements(element, this);
//        System.out.println(bookTitleLink.getText() + " / " + bookAuthor.getText() + " / " + bookPublisher.getText());
    }

    public void openBook(){
        bookTitleLink.click();
    }
}
