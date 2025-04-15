import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public String url = "https://qa.koel.app/";

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowswer(String BaseURL) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);

        driver.get(url);
        url = BaseURL;

    }

    public void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys (email);
    }

    public void providePassword(String pass) {
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(pass);
    }

    public void clickLoginBtn() {
        WebElement clickSubmit = driver.findElement(By.cssSelector("button[type='submit']"));
        clickSubmit.click();
    }

    public void login(String email, String password) {
        provideEmail(email);
        providePassword(password);
        clickLoginBtn();
    }

    public void waitForPlaylistWithName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@class,'playlist')]//span[text()='" + name + "']")));
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
