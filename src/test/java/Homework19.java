import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework19 extends BaseTest {
    @Test
    public void deletePlaylist() throws InterruptedException {
       String expectedMsg = "Deleted playlist \"My Car Playlist.\"";

        login("artemisia.chalkiopoulou@testpro.io", "22002255");

        createPlaylist();
        clickDeletePlaylist();
        Thread.sleep(2000);

        Assert.assertTrue(isDeleteMessageDisplayed(expectedMsg), "Playlist was not deleted successfully.");
    }

   public void createPlaylist() throws InterruptedException {
        WebElement createPlaylistBtn = driver.findElement(By.cssSelector("[data-testid='sidebar-create-playlist-btn']"));
        Thread.sleep(2000);
        createPlaylistBtn.click();

        WebElement newPlaylistBtn = driver.findElement(By.cssSelector("li[data-testid='playlist-context-menu-create-simple']"));
        Thread.sleep(2000);
        newPlaylistBtn.click();

        WebElement playlistName = driver.findElement(By.xpath("//input[@placeholder='↵ to save']"));
        Thread.sleep(2000);
        playlistName.sendKeys("My Car Playlist" + "\n");
   }

    public void clickDeletePlaylist() throws InterruptedException {
        WebElement deletePlaylistBtn = driver.findElement(By.cssSelector("button[title='Delete this playlist']"));
        Thread.sleep(3000);
        deletePlaylistBtn.click();
    }

    public boolean isDeleteMessageDisplayed(String expectedMsg) throws InterruptedException {
        WebElement deleteMsg = driver.findElement(By.xpath("//div[@class='success show']"));
        Thread.sleep(3000);
        return deleteMsg.getText().equals(expectedMsg);
    }

}