package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By avatar = By.cssSelector(".avatar");

    @FindBy(id= "inputProfileCurrentPassword")
    WebElement currentPasswordInput;
    @FindBy(css = "#inputProfileName")
    WebElement profileName;
    @FindBy(css = "#inputProfileEmail")
    WebElement emailInput;
    @FindBy(css = "button[type='submit']")
    WebElement saveBtn;
    @FindBy(id = "inputProfileNewPassword")
    WebElement newPasswordInput;


    By profile = By.cssSelector(".view-profile>span");
    By successMsg = By.cssSelector(".success.show");
    By errorMsg = By.cssSelector(".error.show");

    @FindBy(xpath = "//div[@data-testid='theme-card-violet']")
    WebElement themeVioletButton;

    @FindBy(css = "[data-testid='theme-card-classic']")
    WebElement themeClassicButton;

    By htmlVioletTheme = By.cssSelector("[data-theme='violet']");
    By htmlClassicTheme = By.cssSelector("[data-theme='classic']");
    By themeVioletBackground = By.xpath("//div[@data-testid='theme-card-violet'][@class='theme selected']");


    public void openProfile() {
            wait.until(ExpectedConditions.elementToBeClickable(avatar)).click();
            wait.until(ExpectedConditions.visibilityOf(currentPasswordInput));

    }

    public boolean isAvatarDisplayed(){
        return isElementDisplayed(avatar);
    }

    public void typePassword(String password) {
        currentPasswordInput.click();
        currentPasswordInput.clear();
        currentPasswordInput.sendKeys(password);
    }

    public void typeEmail(String email) {
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void typeNewProfileName(String name) {
        profileName.click();
        profileName.clear();
        profileName.sendKeys(name);
    }

    public void saveProfile() {
        saveBtn.click();
    }

    public String getProfileName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profile));
        return driver.findElement(profile).getText();
    }

    public String deleteProfileName() {
        profileName.clear();
        return driver.findElement(profile).getText();
    }

    public String successMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
        return driver.findElement(successMsg).getText();
    }

    public String errorMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
        return driver.findElement(errorMsg).getText();
    }

    public void clickThemeVioletButton() {
        themeVioletButton.click();
    }

    public void clickThemeClassicButton() {
        themeClassicButton.click();
    }

    public boolean isVioletThemeChosen() {
        return isElementDisplayed(themeVioletBackground);
    }

    public boolean isClassicThemeChosen(){
        return isElementDisplayed(htmlClassicTheme);
    }

    public boolean isVioletBackgroundChosen(){
        return isElementDisplayed(htmlVioletTheme);
    }

    public boolean isElementDisplayed(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    public String getEmailValidationMessage() {
        return emailInput.getAttribute("validationMessage");
    }
//    public void tearDownBrowser() {
//        driver.quit();
//    }

    public void changeEmailAndPassword(String currentPassword, String newEmail, String newPassword) {
        // Change email if provided (not null)
        if (newEmail != null) {
            emailInput.click();
            emailInput.clear();
            emailInput.sendKeys(newEmail);
        }

        // Change password if provided (not null)
        if (newPassword != null) {
            newPasswordInput.click();
            newPasswordInput.clear();
            newPasswordInput.sendKeys(newPassword);
        }

        // Always enter the current password (required for saving)
        currentPasswordInput.click();
        currentPasswordInput.clear();
        currentPasswordInput.sendKeys(currentPassword);

        // Save changes
        saveProfile();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
    }


}




