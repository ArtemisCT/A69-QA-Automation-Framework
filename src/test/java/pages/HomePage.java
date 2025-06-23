package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {


    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    By avatar = By.cssSelector(".avatar");

    public boolean isAvatarDisplayed() {
        return driver.findElement(avatar).isDisplayed();
    }
}
