import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import java.time.Duration;

public class ProfileTests extends BaseTest {

    @Test
    public void changeProfileName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        BasePage basePage = new BasePage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "Artemis@testpro2025");
        profilePage.openProfile();
        profilePage.typePassword("Artemis@testpro2025");
        String name = basePage.generateRandomName();
        System.out.println(name);
        profilePage.typeNewProfileName(name);
        profilePage.saveProfile();
        getDriver().navigate().refresh();
        Thread.sleep(3000);
        String newName = profilePage.getProfileName();
        Assert.assertEquals(newName, name);
    }

    @Test
    public void changeTheme() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "Artemis@testpro2025");
        profilePage.openProfile();

        if (profilePage.isVioletThemeChosen()) {
            profilePage.clickThemeClassicButton();
            Assert.assertTrue(profilePage.isClassicThemeChosen());
        }

        profilePage.clickThemeVioletButton();
        Thread.sleep(1000);
        Assert.assertTrue(profilePage.isVioletThemeChosen());
        Assert.assertTrue(profilePage.isVioletBackgroundChosen());
    }

    @Test
    public void updateProfileWithValidEmailTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "ArtemisCT@testpro2025");
        profilePage.openProfile();
        profilePage.typePassword("ArtemisCT@testpro2025");
        profilePage.typeEmail("john.john@testpro.io");
        profilePage.saveProfile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Assert.assertEquals(profilePage.successMessageIsDisplayed(), "Profile updated.");
    }

    @Test
    public void updateProfileWithInvalidEmail_NoAtSymbolTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.login("john.john@testpro.io", "ArtemisCT@testpro2025");
        profilePage.openProfile();
        profilePage.typePassword("ArtemisCT@testpro2025");
        profilePage.typeEmail("john.johntestpro.io"); // invalid email address: missing '@' symbol
        profilePage.saveProfile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String validationMsg = profilePage.getEmailValidationMessage();
        Assert.assertTrue(validationMsg.contains("include an '@'"), "Expected browser validation message not found.");
    }

    @Test
    public void updateProfileWithInvalidEmail_InvalidDomainTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "ArtemisCT@testpro2025");
        profilePage.openProfile();
        profilePage.typePassword("ArtemisCT@testpro2025");
        profilePage.typeEmail("john.john@email.com"); // invalid email address: wrong domain
        profilePage.saveProfile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String validationMessage = profilePage.getEmailValidationMessage();
        Assert.assertTrue(validationMessage.contains("only certain emails"), "Expected browser validation message not found.");
    }

    @Test
    public void updateProfileWithInvalidEmail_NoDotTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "ArtemisCT@testpro2025");
        profilePage.openProfile();
        profilePage.typePassword("ArtemisCT@testpro2025");
        profilePage.typeEmail("john.john@testproio"); // invalid email address: missing '.' dot
        profilePage.saveProfile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String validationMessage = profilePage.getEmailValidationMessage();
        Assert.assertTrue(validationMessage.contains("include a '.'"), "Expected browser validation message not found.");
    }

    @Test
    public void updateProfileWithInvalidEmail_PlusSignBeforeAtSymbolTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "ArtemisCT@testpro2025");
        profilePage.openProfile();
        profilePage.typePassword("ArtemisCT@testpro2025");
        profilePage.typeEmail("john.john+@testpro.io"); // invalid email address: plus sign before '@' symbol
        profilePage.saveProfile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String validationMessage = profilePage.getEmailValidationMessage();
        Assert.assertTrue(validationMessage.contains("plus"), "Expected browser validation message not found.");
    }

    @Test
    public void updateProfileWithInvalidEmail_AlreadyRegisteredEmailTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "ArtemisCT@testpro2025");
        profilePage.openProfile();
        profilePage.typePassword("ArtemisCT@testpro2025");
        profilePage.typeEmail("artemisia.chalkiopoulou@testpro.io"); // already registered email address
        profilePage.saveProfile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String validationMessage = profilePage.getEmailValidationMessage();
        Assert.assertTrue(validationMessage.contains("already registered"), "Expected browser validation message not found.");
    }

    @Test
    public void loginAfterUpdateProfileWithTheNewEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "ArtemisCT@testpro2025");
        loginPage.clickLoginBtn();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/#!/home"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/#!/home"), "User was not redirected to the Home Page.");
    }

    @Test
    public void loginAfterUpdateProfileWithOldEmail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("john.john@testpro.io", "ArtemisCT@testpro2025");
        loginPage.clickLoginBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/#!/home"), "User was not redirected to the Home Page.");
    }

    @Test
    public void updateProfileWithNoEmail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "ArtemisCT@testpro2025");
        loginPage.clickLoginBtn();

        profilePage.openProfile();
        profilePage.typePassword("ArtemisCT@testpro2025");
        profilePage.saveProfile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String validationMessage = profilePage.getEmailValidationMessage();
        System.out.println(validationMessage);
    }

    @Test
    public void updateProfileEmailWithNoName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "ArtemisCT@testpro2025");
        loginPage.clickLoginBtn();
        profilePage.openProfile();
        profilePage.typePassword("ArtemisCT@testpro2025");
        profilePage.typeEmail("artemisia.chalkiopoulou@testpro2025");
        profilePage.deleteProfileName();
        profilePage.saveProfile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String validationMessage = profilePage.getEmailValidationMessage();
        System.out.println(validationMessage);
    }
}
