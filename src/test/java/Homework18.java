import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;


public class Homework18 extends BaseTest {
    @Test
   public void playSong() {

        login("artemisia.chalkiopoulou@testpro.io", "22002255");

        clickPlay();
        Assert.assertTrue(isSongPlaying());
         }

         public void clickPlay() {
             WebElement playNextBtn = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
             playNextBtn.click();
             WebElement playBtn = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
             playBtn.click();
         }

         public boolean isSongPlaying() {
            WebElement soundBar = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
            return soundBar.isDisplayed();
         }


}

