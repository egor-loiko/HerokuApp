import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HoversTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void checkProfiles() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='figure'][1]/img"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='figure'][1]/div/h5")).getText(), "name: user1", "Name of the user is not correct!");
        driver.findElement(By.cssSelector("[href='/users/1']")).click();
        Assert.assertFalse(driver.findElement(By.tagName("body")).getAttribute("innerText").contains("Not Found"), "Profile page is not found");
        driver.navigate().back();

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='figure'][2]/img"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='figure'][2]/div/h5")).getText(), "name: user2", "Name of the user is not correct!");
        driver.findElement(By.cssSelector("[href='/users/2']")).click();
        Assert.assertFalse(driver.findElement(By.tagName("body")).getAttribute("innerText").contains("Not Found"), "Profile page is not found");
        driver.navigate().back();

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='figure'][3]/img"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='figure'][3]/div/h5")).getText(), "name: user3", "Name of the user is not correct!");
        driver.findElement(By.cssSelector("[href='/users/3']")).click();
        Assert.assertFalse(driver.findElement(By.tagName("body")).getAttribute("innerText").contains("Not Found"), "Profile page is not found");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
