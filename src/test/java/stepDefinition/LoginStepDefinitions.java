//package stepDefinition;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.After;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import pages.LoginPage;
//
//import java.time.Duration;
//
//public class LoginStepDefinitions {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    public void openBrowser() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("disable-notifications");
//        options.addArguments("remote-allow-origins=*");
//
//        driver = new ChromeDriver(options);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    public void openLoginPage() {
//        driver.get("https://qa.koel.app/");
//    }
//
//    @When("I enter email {string}")
//    public void i_enter_email(String email){
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.provideEmail(email);
////        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[type='email']"))).sendKeys(email);
//    }
//
//    public void i_enter_password(String password) {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.providePassword(password);
////        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[type='password']"))).sendKeys(password);
//    }
//
//    public void clickSubmit() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.clickLoginBtn();
////        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[type='submit']"))).click();
//    }
//
//    @Then("I logged in")
//    public void userIsLoggedIn() {
//        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("img.avatar"))).isDisplayed());
//    }
//
//    @After
//    public void closeBrowser() {
//        driver.quit();
//    }
//}
//
