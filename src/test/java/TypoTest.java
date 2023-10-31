import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class TypoTest {
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
    public void loopTyposCheck() {
        driver.get("https://the-internet.herokuapp.com/typos");
        int iterationNumber = 0;
        do {
            System.out.println("Iteration number = " + ++iterationNumber);
            WebElement textWithTypo = driver.findElements(By.tagName("p")).get(1);
            System.out.println(textWithTypo.getText());
            Assert.assertEquals(textWithTypo.getText(), "Sometimes you'll see a typo, other times you won't.", "There is a typo in the text");
            driver.navigate().refresh();
        } while (true);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
