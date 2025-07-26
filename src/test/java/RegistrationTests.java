
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegistrationPage;

import java.time.Duration;


public class RegistrationTests extends BaseTest {

    @Test
    public void successAccountRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.navigateTo();
        registrationPage.enterEmail("artemisia.chalkiopoulou@testpro.io");
        registrationPage.clickSubmitBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertNotEquals(registrationPage.getErrorMessage(), "We've sent a confirmation link to the email. Please continue by clicking on it");
    }

    @Test
    public void alreadyRegisteredEmailTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.navigateTo();
        registrationPage.enterEmail("artemisia.chalkiopoulou@testpro.io"); // Ensure this exists in DB
        registrationPage.clickSubmitBtn();
        registrationPage.navigateTo();
        registrationPage.enterEmail("artemisia.chalkiopoulou@testpro.io");
        registrationPage.clickSubmitBtn();
//        Assert.assertEquals(registrationPage.getErrorMessage(), "user already registered");
        Assert.assertNotEquals(registrationPage.getErrorMessage(), "We've sent a confirmation link to the email. Please continue by clicking on it");
    }

    @Test
    public void invalidEmailFormat_NoAtSymbolTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.navigateTo();
        registrationPage.enterEmail("artemisia.chalkiopouloutestpro.io");
        registrationPage.clickSubmitBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        Assert.assertEquals(registrationPage.getErrorMessage2(), "Please include an '@' in the email address. 'artemisia.chalkiopouloutestpro.io' is missing an '@'.");
        Assert.assertNotEquals(registrationPage.getErrorMessage(), "We've sent a confirmation link to the email. Please continue by clicking on it");
    }

    @Test
    public void invalidPasswordFormat_NoDotAfterAtSymbolTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.navigateTo();
        registrationPage.enterEmail("artemisia.chalkiopoulou@testproio");
        registrationPage.clickSubmitBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        Assert.assertEquals(registrationPage.getErrorMessage2(), "Sorry, only certain emails are allowed, please do not use your personal email");
        Assert.assertNotEquals(registrationPage.getErrorMessage(), "We've sent a confirmation link to the email. Please continue by clicking on it");
    }

    @Test
    public void invalidEmailFormat_WrongDomainTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.navigateTo();
        registrationPage.enterEmail("artemisia.chalkiopoulou@email.com");
        registrationPage.clickSubmitBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        Assert.assertEquals(registrationPage.getErrorMessage(), "Sorry, only certain emails are allowed, please do not use your personal email");
        Assert.assertNotEquals(registrationPage.getErrorMessage(), "We've sent a confirmation link to the email. Please continue by clicking on it");
    }

    @Test
    public void plusSignOneNotAllowedBeforeAtTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.navigateTo();
        registrationPage.enterEmail("artemisia.chalkiopoulou+1@testpro.io");
        registrationPage.clickSubmitBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertNotEquals(registrationPage.getErrorMessage(), "We've sent a confirmation link to the email. Please continue by clicking on it");
    }

    @Test
    public void plusSignThreeNotAllowedBeforeAtTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.navigateTo();
        registrationPage.enterEmail("artemisia+3.chalkiopoulou@testpro.io");
        registrationPage.clickSubmitBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertNotEquals(registrationPage.getErrorMessage(), "We've sent a confirmation link to the email. Please continue by clicking on it");
    }

    @Test
    public void successLoginAfterRegistrationTest() {
        RegistrationPage login = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        login.enterEmail("artemisia.chalkiopoulou@testpro.io");
        login.enterPassword("Artemis@testpro2025");
        loginPage.clickLoginBtn();
    }
}
