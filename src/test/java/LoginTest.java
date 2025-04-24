import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    public void loginEmailPassword() {
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
        getDriver().quit();
    }

    @Test
    public void loginSuccessTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.provideEmail ("artemisia.chalkiopoulou@testpro.io")
                .providePassword("22002255")
                .clickLoginBtn();

        WebElement avatar = getDriver().findElement(By.cssSelector(".avatar"));
        Assert.assertTrue(avatar.isDisplayed());
        Thread.sleep(5000);
    }

    @Test
    public void loginEmptyPasswordTest() {
        provideEmail ("artemisia.chalkiopoulou@testpro.io");
        clickLoginBtn();
    }

    @Test
    public void loginInvalidEmailTest() {
        provideEmail("artemisia.chalkiopoulou@testpro.io");
        providePassword("22002244");
        clickLoginBtn();
    }
}
