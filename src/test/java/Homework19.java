import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework19 extends BaseTest {
    @Test
    public void deletePlaylist() {
       String delPlaylistMsgDisplay = "Deleted playlist \"My Songs 2 .\"";

        login("artemisia.chalkiopoulou@testpro.io", "22002255");

        Assert.assertTrue(delPlaylistMsg(), delPlaylistMsgDisplay);
    }
public void openplaylist(){
        WebElement emptyPlaylist = driver.findElement(By.cssSelector("li[class='playlist playlist']"));
        emptyPlaylist.click();
}

public void clickDeletePlaylist() {
        WebElement deletePlaylistBtn = driver.findElement(By.cssSelector("button[title='Delete this playlist']"));
        deletePlaylistBtn.click();
}

public boolean delPlaylistMsg() {
        WebElement deleteMsg = driver.findElement(By.xpath("//div[@class='success show']"));
    return true;
}

}