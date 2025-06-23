import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.PlaylistsPage;
import pages.SongsPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class SongsTests extends BaseTest {

    @Test
    public void checkVisibilityTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.provideEmail("artemisia.chalkiopoulou@testpro.io")
                .providePassword("22002255")
                .clickLoginBtn();

        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title")));
        String text = title.getText();
        System.out.println(text);
        System.out.println("Is element invisible? === " + wait.until(invisibilityOfElementLocated(By.cssSelector("title"))));
    }

    @Test
    public void addSongToPlaylist() {
        LoginPage loginPage = new LoginPage(getDriver());
        SongsPage songsPage = new SongsPage(getDriver());
        PlaylistsPage playlistsPage = new PlaylistsPage(getDriver());
        BasePage basePage = new BasePage(getDriver());

        String text = "Philip Ravenel - The Greatest Century";
        String playlistName = basePage.generateRandomPlaylistName();

        loginPage.login("artemisia.chalkiopoulou@testpro.io", "22002255");

        songsPage.searchForSong(text);
        songsPage.clickViewAllBtn();
        songsPage.clickFirstSearchResultSong();
        songsPage.clickAddToPlaylistBtn();
        playlistsPage.createNewPlaylistWhileAddingSong(playlistName);

        Assert.assertTrue(basePage.isSuccessBannerDisplayed());
        Assert.assertEquals(text, songsPage.getSongName());
    }

    @Test
    public void playSong() {
        LoginPage loginPage = new LoginPage(getDriver());
        SongsPage songsPage = new SongsPage(getDriver());
        loginPage.provideEmail("artemisia.chalkiopoulou@testpro.io")
                .providePassword("22002255")
                .clickLoginBtn();

        WebElement playOrResumeBtn = getDriver().findElement(By.cssSelector("[title='Play or resume']"));
        new Actions(getDriver())
                .moveToElement(playOrResumeBtn)
                .perform();
        playOrResumeBtn.click();

        WebElement pauseBtn = getDriver().findElement(By.cssSelector("[data-testid='pause-btn']"));
        Assert.assertTrue(pauseBtn.isDisplayed());

        WebElement equalizer = getDriver().findElement(By.cssSelector("[alt='Sound bars']"));
        Assert.assertTrue(equalizer.isDisplayed());
    }
}

