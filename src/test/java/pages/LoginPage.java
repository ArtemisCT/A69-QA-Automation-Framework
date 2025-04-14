package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    //constructor
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //locators
    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By clickSubmit = By.cssSelector("button[type='submit']");



    public void provideEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void providePassword(String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(pass);
    }

    public void clickLoginBtn() {
        driver.findElement(clickSubmit).click();
    }

    public void login(String email, String password) {
        provideEmail(email);
        providePassword(password);
        clickLoginBtn();
    }




}
