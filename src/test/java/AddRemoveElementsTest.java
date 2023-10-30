import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.time.DurationUtils;
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
import java.util.List;


public class AddRemoveElementsTest {
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
    public void addRemoveElements() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.cssSelector("[onclick='addElement()']")).click();
        driver.findElement(By.cssSelector("[onclick='addElement()']")).click();
        List<WebElement> webElementListBeforeRemoval = driver.findElements(By.className("added-manually"));
        webElementListBeforeRemoval.get(1).click();
        List<WebElement> webElementListAfterRemoval = driver.findElements(By.className("added-manually"));
        Assert.assertEquals(webElementListAfterRemoval.size(), 1, "Quantity of elements is not correct after removal!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
