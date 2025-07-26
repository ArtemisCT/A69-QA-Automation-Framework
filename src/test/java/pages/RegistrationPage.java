package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "input[type='email']")
    WebElement emailField;
    @FindBy(css = "input[type='password']")
    WebElement passwordField;
    @FindBy(xpath = "//input[@id='button']")
    WebElement clickSubmit;
    @FindBy(css = "input[type='password']")

    By errorMsg = By.cssSelector(".messages");
    By errorMsg2 = By.xpath("//div[@class='errors']");

    public RegistrationPage navigateTo() {
        driver.get("https://qa.koel.app/registration");
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }
    public RegistrationPage enterPassword(String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(pass);
        return this;
    }
    public RegistrationPage clickSubmitBtn() {
        clickSubmit.click();
        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }

    public String getErrorMessage2() {
        return driver.findElement(errorMsg2).getText();
    }
}
