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


public class CheckboxesTest {
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
    public void checkboxesStatus() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement firstCheckbox = driver.findElements(By.cssSelector("[type=checkbox]")).get(0);
        WebElement secondCheckbox = driver.findElements(By.cssSelector("[type=checkbox]")).get(1);
        Assert.assertFalse(firstCheckbox.isSelected(), "Checkbox is selected");
        firstCheckbox.click();
        Assert.assertTrue(firstCheckbox.isSelected(), "Checkbox is Not selected");
        Assert.assertTrue(secondCheckbox.isSelected(), "Checkbox is Not selected");
        secondCheckbox.click();
        Assert.assertFalse(secondCheckbox.isSelected(), "Checkbox is selected");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
