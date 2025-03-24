import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework16 {
    @Test
    public void registrationNavigation() throws InterruptedException {

//      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://qa.koel.app/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
        WebElement registration = driver.findElement(By.xpath("//a[@href='registration']"));
        registration.click();
        Thread.sleep(3000);
        WebElement registerHeading = driver.findElement(By.xpath("//h2[contains(text(), 'Register new account or')]"));
        Assert.assertTrue(registerHeading.isDisplayed());
        Thread.sleep(3000);
        driver.quit();
    }
}
