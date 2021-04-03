import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class MyBasicTest {
    static WebDriver driver;

    @BeforeMethod
    public void preparation(){
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }

    static WebElement waitClickable(String xPath, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

    static WebElement waitClickable(String xPath, long timeoutSeconds, long intervalSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds), Duration.ofSeconds(intervalSeconds))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

    static WebElement waitPresent(String xPath, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
    }

}
