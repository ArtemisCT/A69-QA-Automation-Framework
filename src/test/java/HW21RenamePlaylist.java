import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HW21RenamePlaylist extends BaseTest {

    @Test
    public void renamePlaylist() {

        login("artemisia.chalkiopoulou@testpro.io", "22002255");

        String originalPlaylist = "New Playlist";
        String renamedPlaylist = "Homework 21 Renamed";

        createPlaylist();
        renamePlaylistWithActions();
        Assert.assertTrue(isPlaylistRenamed());
    }

    public void createPlaylist() {
        WebElement createPlaylistBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title='Create a new playlist']")));
        actions.moveToElement(createPlaylistBtn).click().perform();

        WebElement newPlaylistBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='playlist-context-menu-create-simple']")));
        actions.moveToElement(newPlaylistBtn).click().perform();

        WebElement namePlaylistField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='↵ to save']")));
        actions.moveToElement(namePlaylistField).sendKeys("New Playlist" + Keys.ENTER);
        System.out.println(namePlaylistField.getText());
    }

    public void renamePlaylistWithActions() {
        WebElement playlistElement = driver.findElement(By.xpath("//*[@id=\"playlists\"]/ul/li[5]/a"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions.moveToElement(playlistElement).doubleClick().perform();
        playlistElement.sendKeys("My Study Playlist" + Keys.ENTER);
    }

    public boolean isPlaylistRenamed() {
        WebElement newPlaylistElement = driver.findElement(By.xpath("//div[@class='success show']"));
        return true;
    }

}
