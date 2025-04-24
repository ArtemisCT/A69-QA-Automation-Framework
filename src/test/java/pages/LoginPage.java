package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    //constructor
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //locators with PageFactory
    @FindBy(css = "input[type='email']")
            WebElement emailField;
    @FindBy(css = "input[type='password']")
            WebElement passwordField;
    @FindBy(css = "button[type='submit']")
            WebElement clickSubmit;

//    By emailField = By.cssSelector("input[type='email']");
//    By passwordField = By.cssSelector("input[type='password']");
//    By clickSubmit = By.cssSelector("button[type='submit']");

    // Methods with PageFactory
    public LoginPage provideEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
//        driver.findElement(emailField).click();
        emailField.click();
//        driver.findElement(emailField).clear();
        emailField.clear();
//        driver.findElement(emailField).sendKeys(email);
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage providePassword(String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
//        driver.findElement(passwordField).click();
        passwordField.click();
//        driver.findElement(passwordField).clear();
        passwordField.clear();
//        driver.findElement(passwordField).sendKeys(pass);
        passwordField.sendKeys(pass);
        return this;
    }

    public LoginPage clickLoginBtn() {
//        driver.findElement(clickSubmit);
                clickSubmit.click();
        return this;
    }

    public LoginPage login(String email, String password) {
        provideEmail(email);
        providePassword(password);
        clickLoginBtn();
        return this;
    }




}
