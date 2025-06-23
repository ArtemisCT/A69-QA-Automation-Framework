package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "input[type='email']")
            WebElement emailField;
    @FindBy(css = "input[type='password']")
            WebElement passwordField;
    @FindBy(css = "button[type='submit']")
            WebElement clickSubmit;

    public LoginPage provideEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage providePassword(String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(pass);
        return this;
    }

    public LoginPage clickLoginBtn() {
        clickSubmit.click();
        return this;
    }

    public boolean isSubmitDisplayed() {
        return clickSubmit.isDisplayed();
    }

    public LoginPage login(String email, String password) {
        provideEmail(email);
        providePassword(password);
        clickLoginBtn();
        return this;
    }
}
