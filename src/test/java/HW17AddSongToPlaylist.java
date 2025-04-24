
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HW17AddSongToPlaylist {
    @Test

    public void addSongToPlaylist() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = "https://qa.koel.app/";
        driver.navigate().to(url);
        Assert.assertTrue(driver.getPageSource().contains(url));
        Thread.sleep(3000);

        WebElement provideEmail = driver.findElement(By.cssSelector("[type='email']"));
        provideEmail.sendKeys("artemisia.chalkiopoulou@testpro.io");
        WebElement providePassword = driver.findElement(By.cssSelector("[type='password']"));
        providePassword.sendKeys("22002255");
        WebElement login = driver.findElement(By.cssSelector("[type='submit']"));
        login.click();
        Thread.sleep(3000);

        WebElement search = driver.findElement(By.cssSelector("[type='search']"));
        search.sendKeys("century");
        Thread.sleep(3000);

        WebElement ViewAllButton = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"));
        ViewAllButton.click();
        Thread.sleep(3000);

        WebElement firstSong = driver.findElement(By.xpath("//td[normalize-space()='Philip Ravenel - The Greatest Century']"));
        firstSong.click();
        Thread.sleep(3000);

        WebElement addTo = driver.findElement(By.cssSelector("button[title='Add selected songs to…']"));
        addTo.click();
        Thread.sleep(3000);

        WebElement mySongsPlaylist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[@class='playlist'][normalize-space()='My Songs']"));
        mySongsPlaylist.click();
        Thread.sleep(3000);
// There is no message when I add a song to either lists. This is why I didn't include an "assert" method.
        driver.quit();

    }
}
