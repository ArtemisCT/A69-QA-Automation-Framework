package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.ProfilePage;

import static org.bouncycastle.cms.RecipientId.password;

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
    @FindBy(css = "span[class='name']")
    WebElement profileIcon;


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
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".avatar")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.name")));

        return this;
    }

    public Object openProfile() {
//        wait.until(ExpectedConditions.visibilityOf(profileIcon));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.name")));

        profileIcon.click();
        return null;
    }
}
