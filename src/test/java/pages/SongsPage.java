package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SongsPage extends BasePage {
    public SongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "[type='search']")
    WebElement searchInput;
    @FindBy(xpath = "//button[@data-test='view-all-songs-btn']")
    WebElement ViewAllButton;


    public void searchForSong(String text) {
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys(text);
    }

    public void clickViewAllBtn() {
        ViewAllButton.click();
    }

    public void clickFirstSearchResultSong() {
        List<WebElement> songsInResults = driver.findElements(By.cssSelector(".search-results .song-item .title"));
        songsInResults.get(0).click();
    }

    public void clickAddToPlaylistBtn() {
        WebElement addToBtn = driver.findElement(By.cssSelector("[data-test='add-to-btn']"));
        addToBtn.click();
    }

    public String getSongName() {
        WebElement songName = driver.findElement(By.cssSelector("#playlistWrapper .song-item .title"));
        String songText = songName.getText();
        return songText;
    }
}
