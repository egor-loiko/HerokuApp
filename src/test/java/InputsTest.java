import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class InputsTest {
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
    public void inputPositiveNumberByArrows() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.sendKeys(Keys.ARROW_UP);
        inputField.sendKeys(Keys.ARROW_UP);
        Assert.assertEquals(inputField.getAttribute("value"), "2", "Incorrect number is present in the input field");
    }

    @Test
    public void inputNegativeNumberByArrows() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.sendKeys(Keys.ARROW_DOWN);
        inputField.sendKeys(Keys.ARROW_DOWN);
        Assert.assertEquals(inputField.getAttribute("value"), "-2", "Incorrect number is present in the input field");
    }

    @Test
    public void inputPositiveNumber() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.sendKeys("18");
        Assert.assertEquals(inputField.getAttribute("value"), "18", "Incorrect number is present in the input field");
    }

    @Test
    public void inputNegativeNumber() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.sendKeys("-158");
        Assert.assertEquals(inputField.getAttribute("value"), "-158", "Incorrect number is present in the input field");
    }

    @Test
    public void inputNotNumbers() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.sendKeys("!@#$%%ThisIsText");
        Assert.assertEquals(inputField.getAttribute("value"), "", "Incorrect data is present in the input field");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
