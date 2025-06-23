import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import java.time.Duration;

public class LoginTests extends BaseTest {

    @Test
    public void loginSucceedTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        ProfilePage profilePage = new ProfilePage(getDriver());
        loginPage.provideEmail ("artemisia.chalkiopoulou@testpro.io")
                .providePassword("22002255")
                .clickLoginBtn();

        Assert.assertTrue(profilePage.isAvatarDisplayed());
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void loginEmptyPasswordTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.provideEmail ("artemisia.chalkiopoulou@testpro.io").clickLoginBtn();
        Assert.assertTrue(loginPage.isSubmitDisplayed());
    }

    @Test
    public void loginInvalidEmailTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("wrong.email@testpro.io", "22002244");
        Assert.assertTrue(loginPage.isSubmitDisplayed());
    }
}
