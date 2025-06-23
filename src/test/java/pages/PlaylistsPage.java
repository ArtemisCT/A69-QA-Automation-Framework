package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getDriver;

public class PlaylistsPage extends BasePage {
    public PlaylistsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By plusBtn = By.cssSelector("[data-testid='sidebar-create-playlist-btn']");
    By createNewPlaylistBtn = By.cssSelector("[data-testid='playlist-context-menu-create-simple']");By playlistNameInput = By.cssSelector(".create input");
    By playlistHeader = By.cssSelector("#playlistWrapper h1");
    By deletePlaylistBtn = By.cssSelector(".btn-delete-playlist");
    By playlists = By.cssSelector("#playlists a");

    public void createPlaylistWithPlusBtn(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(plusBtn));
        driver.findElement(plusBtn).click();

        wait.until(ExpectedConditions.elementToBeClickable(createNewPlaylistBtn)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(playlistNameInput));
        driver.findElement(playlistNameInput).click();
        driver.findElement(playlistNameInput).clear();
        driver.findElement(playlistNameInput).sendKeys(playlistName);

        new Actions(driver)
                .keyDown(Keys.ENTER)
                .perform();
    wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(playlistHeader), playlistName));
    }

    public void deletePlaylist(){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(deletePlaylistBtn)));
        driver.findElement(deletePlaylistBtn).click();
    }

    public List<String> getAllPlaylistNames(){
        List<WebElement> playlistElements = driver.findElements(playlists);
        List<String> playlistNames = new ArrayList<>();

        for (int i = 0; i < playlistElements.size(); i++) {
            String playlistName1 = playlistElements.get(i).getText();
            playlistNames.add(playlistName1);
        }
        return playlistNames;
    }


    public void createNewPlaylistWhileAddingSong(String playlistName) {
        WebElement newPlaylistNameInput = driver.findElement(By.cssSelector("[id='songResultsWrapper'] [placeholder='Playlist name']"));
        newPlaylistNameInput.click();
        newPlaylistNameInput.clear();
        newPlaylistNameInput.sendKeys(playlistName);
        newPlaylistNameInput.click();
        newPlaylistNameInput.clear();
        newPlaylistNameInput.sendKeys(playlistName);

        new Actions(driver)
                .keyDown(Keys.ENTER)
                .perform();
    }
}
