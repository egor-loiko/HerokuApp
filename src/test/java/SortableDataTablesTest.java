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


public class SortableDataTablesTest {
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
    public void checkTable1Content() {
        driver.get("https://the-internet.herokuapp.com/tables");
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[1]/td[1]")).getText(), "Smith", "Incorrect data in the Table 1, Row 1, Cell 1");
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[2]/td[2]")).getText(), "Frank", "Incorrect data in the Table 1, Row 2, Cell 2");
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[3]/td[3]")).getText(), "jdoe@hotmail.com", "Incorrect data in the Table 1, Row 3, Cell 3");
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[4]/td[4]")).getText(), "$50.00", "Incorrect data in the Table 1, Row 4, Cell 4");
    }

    @Test
    public void checkTable2Content() {
        driver.get("https://the-internet.herokuapp.com/tables");
        Assert.assertEquals(driver.findElement(By.xpath("//tr[1]/td[@class = 'last-name']")).getText(), "Smith", "Incorrect data in the Table 2, Row 1, Cell 1");
        Assert.assertEquals(driver.findElement(By.xpath("//tr[2]/td[@class = 'first-name']")).getText(), "Frank", "Incorrect data in the Table 2, Row 2, Cell 2");
        Assert.assertEquals(driver.findElement(By.xpath("//tr[3]/td[@class = 'email']")).getText(), "jdoe@hotmail.com", "Incorrect data in the Table 2, Row 3, Cell 3");
        Assert.assertEquals(driver.findElement(By.xpath("//tr[4]/td[@class = 'dues']")).getText(), "$50.00", "Incorrect data in the Table 2, Row 4, Cell 4");
        Assert.assertEquals(driver.findElement(By.xpath("//tr[4]/td[@class = 'web-site']")).getText(), "http://www.timconway.com", "Incorrect data in the Table 2, Row 4, Cell 4");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
