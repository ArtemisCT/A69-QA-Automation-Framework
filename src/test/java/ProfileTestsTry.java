
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.ProfilePage;
import utils.Config;
import java.time.Duration;

public class ProfileTestsTry extends BaseTest {

    private static final String PRIMARY_EMAIL           = Config.get("user.primary.email");
    private static final String PRIMARY_PASSWORD        = Config.get("user.primary.password");
    private static final String PRIMARY_UPDATED_PASSWORD= Config.get("user.primary.updatedPassword");
    private static final String SECONDARY_EMAIL         = Config.get("user.secondary.email");
    private static final String SECONDARY_PASSWORD      = Config.get("user.secondary.password");

//    @BeforeMethod
//    public void setUp() {
//        driver.manage().deleteAllCookies();
//        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
//        driver.get(Config.get("app.url"));
//    }
//
//    @BeforeMethod
//    public void goToLogin() {
//        driver.manage().deleteAllCookies();
//        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
//        driver.get(Config.get("app.url"));
//    }
@BeforeMethod
public void setUp() {
    driver.manage().deleteAllCookies();
    ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    driver.get(Config.get("app.url"));
}


    @Test
    public void changeProfileName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        BasePage basePage = new BasePage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.login(PRIMARY_EMAIL, PRIMARY_PASSWORD);

        profilePage.openProfile();
        profilePage.typePassword(PRIMARY_PASSWORD);

        String name = basePage.generateRandomName();
        System.out.println(name);
        profilePage.typeNewProfileName(name);
        profilePage.saveProfile();

        getDriver().navigate().refresh();
        Thread.sleep(3000);
        Assert.assertEquals(profilePage.getProfileName(), name);
    }

    @Test
    public void changeTheme() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.login(PRIMARY_EMAIL, PRIMARY_PASSWORD);
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

        loginPage.login(PRIMARY_EMAIL, PRIMARY_UPDATED_PASSWORD);
        profilePage.openProfile();
        profilePage.typePassword(PRIMARY_UPDATED_PASSWORD);
        profilePage.typeEmail(SECONDARY_EMAIL);
        profilePage.saveProfile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Assert.assertEquals(profilePage.successMessageIsDisplayed(), "Profile updated.");
    }

    @Test
    public void updateProfileWithInvalidEmail_InvalidDomainTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.login(PRIMARY_EMAIL, PRIMARY_UPDATED_PASSWORD);
        profilePage.openProfile();
        profilePage.typePassword(PRIMARY_UPDATED_PASSWORD);
        profilePage.typeEmail("john.john@email.com"); // invalid email address: wrong domain
        profilePage.saveProfile();

        String validationMessage = profilePage.getEmailValidationMessage();
        Assert.assertTrue(validationMessage.contains("only certain emails"), "Expected browser validation message not found.");
    }

    @Test
    public void updateProfileWithInvalidEmail_NoDotTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.login(PRIMARY_EMAIL, PRIMARY_UPDATED_PASSWORD);
        profilePage.openProfile();
        profilePage.typePassword(PRIMARY_UPDATED_PASSWORD);
        profilePage.typeEmail("john.john@testproio"); // invalid email address: missing '.' dot
        profilePage.saveProfile();

        String validationMessage = profilePage.getEmailValidationMessage();
        Assert.assertTrue(validationMessage.contains("include a '.'"), "Expected browser validation message not found.");
    }

    @Test
    public void updateProfileWithInvalidEmail_PlusSignBeforeAtSymbolTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.login(PRIMARY_EMAIL, PRIMARY_UPDATED_PASSWORD);
        profilePage.openProfile();
        profilePage.typePassword(PRIMARY_UPDATED_PASSWORD);
        profilePage.typeEmail("john.john+@testpro.io"); // invalid email address: plus sign before '@' symbol
        profilePage.saveProfile();

        String validationMessage = profilePage.getEmailValidationMessage();
        Assert.assertTrue(validationMessage.contains("plus"), "Expected browser validation message not found.");
    }

    @Test
    public void updateProfileWithInvalidEmail_AlreadyRegisteredEmailTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.login(PRIMARY_EMAIL, PRIMARY_UPDATED_PASSWORD);
        profilePage.openProfile();
        profilePage.typePassword(PRIMARY_UPDATED_PASSWORD);
        profilePage.typeEmail(PRIMARY_EMAIL);
        profilePage.saveProfile();

        String validationMessage = profilePage.getEmailValidationMessage();
        Assert.assertTrue(validationMessage.contains("already registered"), "Expected browser validation message not found.");
    }

    @Test
    public void updateProfileWithNoEmail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.login(PRIMARY_EMAIL, PRIMARY_UPDATED_PASSWORD);
        loginPage.clickLoginBtn();

        profilePage.openProfile();
        profilePage.typePassword(PRIMARY_UPDATED_PASSWORD);
        profilePage.saveProfile();
        String validationMessage = profilePage.getEmailValidationMessage();

        Assert.assertFalse(validationMessage.isEmpty(),
                "Expected a validation message when no email is provided.");
    }

    @Test
    public void updateProfileEmailWithNoName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.login(PRIMARY_EMAIL, PRIMARY_UPDATED_PASSWORD);
        loginPage.clickLoginBtn();
        profilePage.openProfile();
        profilePage.typePassword(PRIMARY_UPDATED_PASSWORD);
        profilePage.typeEmail(PRIMARY_EMAIL);
        profilePage.deleteProfileName();
        profilePage.saveProfile();

        String validationMessage = profilePage.getEmailValidationMessage();
        Assert.assertFalse(validationMessage.isEmpty(),
                "Expected a validation message when name is removed.");
    }

    @Test
    public void loginAfterUpdateProfileWithTheNewEmail() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(PRIMARY_EMAIL, PRIMARY_UPDATED_PASSWORD)
                .clickLoginBtn();

        wait.until(ExpectedConditions.urlContains("/#!/home"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/#!/home"), "User was not redirected to the Home Page.");
    }

    @Test
    public void updateProfileWithInvalidEmail_NoAtSymbolTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.login(SECONDARY_EMAIL, SECONDARY_PASSWORD);
        profilePage.openProfile();
        profilePage.typePassword(SECONDARY_PASSWORD);

        profilePage.typeEmail("john.johntestpro.io"); // invalid email address: missing '@' symbol
        profilePage.saveProfile();

        String validationMsg = profilePage.getEmailValidationMessage();
        Assert.assertTrue(validationMsg.contains("include an '@'"), "Expected browser validation message not found.");
    }

    @Test
    public void loginAfterUpdateProfileWithOldEmail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(SECONDARY_EMAIL, SECONDARY_PASSWORD);
        loginPage.clickLoginBtn();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("/#!/home")));
        Assert.assertFalse(driver.getCurrentUrl().contains("/#!/home"), "User should not redirected to the Home Page.");
    }

    @AfterSuite(alwaysRun = true)
    public void resetProfileToPrimary() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            ProfilePage profilePage = new ProfilePage(driver);

            loginPage.login(Config.get("user.primary.email"), Config.get("user.primary.updatedPassword"));
            profilePage.openProfile();
            profilePage.changeEmailAndPassword(
                    Config.get("user.primary.updatedPassword"),
                    Config.get("user.primary.email"),
                    Config.get("user.primary.password")
            );
        } catch (Exception e) {
            System.err.println("Error in @AfterSuite cleanup: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    }

}

