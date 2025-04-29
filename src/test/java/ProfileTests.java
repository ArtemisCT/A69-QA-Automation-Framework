import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "22002255");
        profilePage.openProfile();
        profilePage.typePassword("22002255");
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
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "22002255");
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

}
