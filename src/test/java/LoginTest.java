import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    public void loginEmailPassword() {
        Assert.assertEquals(driver.getCurrentUrl(), url);
        driver.quit();
    }

    @Test
    public void loginSuccessTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail ("artemisia.chalkiopoulou@testpro.io")
                .providePassword("22002255")
                .clickLoginBtn();

        WebElement avatar = driver.findElement(By.cssSelector(".avatar"));
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
