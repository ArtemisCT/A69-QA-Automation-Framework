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
    @FindBy(css = ".btn-submit")
    WebElement saveBtn;

    By profile = By.cssSelector(".view-profile>span");

    @FindBy(xpath = "//div[@data-testid='theme-card-violet']")
    WebElement themeVioletButton;

    @FindBy(css = "[data-testid='theme-card-classic']")
    WebElement themeClassicButton;

    By htmlVioletTheme = By.cssSelector("[data-theme='violet']");
    By htmlClassicTheme = By.cssSelector("[data-theme='classic']");
    By themeVioletBackground = By.xpath("//div[@data-testid='theme-card-violet'][@class='theme selected']");


    public void openProfile() throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(avatar)).click();
    }

    public boolean isAvatarDisplayed(){
        return isElementDisplayed(avatar);
    }

    public void typePassword(String password) {
        currentPasswordInput.click();
        currentPasswordInput.clear();
        currentPasswordInput.sendKeys(password);
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
}
