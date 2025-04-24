import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.net.URL;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public String url = "https://qa.koel.app/";
    public static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    @BeforeSuite
    static void setupClass(){
//        WebDriverManager.chromedriver().setup();
    }
    public static WebDriver getDriver(){
        return threadDriver.get();
    }
    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowswer(String BaseURL) throws MalformedURLException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");

//        driver = new ChromeDriver(options);
        threadDriver.set(pickBrowser(System.getProperty("browser")));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        actions = new Actions(getDriver());

        getDriver().get(url);
        url = BaseURL;
    }


    public void provideEmail(String email) {
        WebElement emailField = getDriver().findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void providePassword(String pass) {
        WebElement passwordField = getDriver().findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(pass);
    }

    public void clickLoginBtn() {
        WebElement clickSubmit = getDriver().findElement(By.cssSelector("button[type='submit']"));
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

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

//        driver.quit();
        threadDriver.get().close();
        threadDriver.remove();
    }

    public void openUrl(String url) {
        getDriver().get(url);
    }


    public WebDriver getLambdaDriver() throws MalformedURLException {

        String hubURL = "https://hub.lambdatest.com/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "137.0");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "chartemisia");
        ltOptions.put("accessKey", "LT_Wyhs3YHleuEcUjJ3VGoVAMuu1yw5gcLRkQ5ffwkUlYOCXjK");
        ltOptions.put("build", "MyTests");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("project", "Koel App");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");

        capabilities.setCapability("LT:Options", ltOptions);
        return new RemoteWebDriver(new URL(hubURL), capabilities);


    }


public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String gridURL = "http://192.168.1.166:4444";

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();

                case "safari":
                WebDriverManager.safaridriver().setup();
                return driver = new SafariDriver();

                case "edge":
                WebDriverManager.edgedriver().setup();
                return driver = new EdgeDriver();

                case "grid-firefox":
                capabilities.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);

                case "grid-safari":
                capabilities.setCapability("browserName", "safari");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);

            case "cloud":
                return getLambdaDriver();

                default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                options.addArguments("--start-maximized");
                return driver = new ChromeDriver(options);

        }
    }

}