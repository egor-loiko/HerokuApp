import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class NotificationMessagesTest {
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
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        for (int i = 0; i < 10; i++) {
            driver.findElement(By.cssSelector("[href='/notification_message']")).click();
            String notificationMessage = driver.findElement(By.id("flash")).getText();
            if (notificationMessage.contains("Action successful")) {
                Assert.assertEquals(notificationMessage, "Action successful\n×", "Text of the message is not correct!");
            } else {
                Assert.assertEquals(notificationMessage, "Action unsuccesful, please try again\n×", "Text of the message is not correct!");
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
