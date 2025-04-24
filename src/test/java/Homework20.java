import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;

public class Homework20 extends BaseTest {
    @Test
    public void deletePlaylist() throws InterruptedException {
        String expectedMsg = "Deleted playlist \"My Car Playlist\"";

        login("artemisia.chalkiopoulou@testpro.io", "22002255");

        createPlaylist();
        clickDeletePlaylist();

        Assert.assertTrue(isDeleteMessageDisplayed(expectedMsg), "Playlist was not deleted successfully.");
    }

    public void createPlaylist() {
        WebElement createPlaylistBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid='sidebar-create-playlist-btn']")));
        createPlaylistBtn.click();

        WebElement newPlaylistBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("li[data-testid='playlist-context-menu-create-simple']")));
        newPlaylistBtn.click();

        WebElement playlistNameInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='↵ to save']")));
        playlistNameInput.sendKeys("My Car Playlist\n");
    }

    public void clickDeletePlaylist() throws InterruptedException {
        WebElement deletePlaylistBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[title='Delete this playlist']")));
        deletePlaylistBtn.click();
    }

    public boolean isDeleteMessageDisplayed(String expectedMsg) {
        WebElement deleteMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='success show']")));
        return deleteMsg.getText().equals(expectedMsg);
    }

}