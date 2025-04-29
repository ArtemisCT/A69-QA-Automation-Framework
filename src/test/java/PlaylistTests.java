import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.PlaylistsPage;

import java.time.Duration;
import java.util.List;

public class PlaylistTests extends BaseTest {

    @Test
    public void deletePlaylist() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        PlaylistsPage playlistsPage = new PlaylistsPage(getDriver());
        BasePage basePage = new BasePage(getDriver());

        String playlistName = basePage.generateRandomPlaylistName();
        loginPage.login("artemisia.chalkiopoulou@testpro.io", "22002255");
        playlistsPage.createPlaylistWithPlusBtn(playlistName);
        Thread.sleep(1000);
        playlistsPage.deletePlaylist();
        Thread.sleep(1000);
        Assert.assertTrue(basePage.isSuccessBannerDisplayed());
        driver.navigate().refresh();
        List<String> playlistNames = playlistsPage.getAllPlaylistNames();
        Assert.assertFalse(playlistNames.contains(playlistName));
    }
}