package DemoTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class CrossBrowserTesting {
    public WebDriver driver;
    private String username = "kevinlbthai79";
    private String accessKey = "vswam9dBUfX2S8qD1U33m7afq8vfxxqvc8TOqBQnQUST8ATZb8";
    private String hub = "@hub.lamdatest.com/wd/hub";

    DesiredCapabilities caps = new DesiredCapabilities();

    @Parameters({"Browser", "Version", "Platform" })
    @BeforeMethod
    public void setUp(String browser, String version, String platform) {
        /*caps.setCapability("build", "2.1");
        caps.setCapability("name", "Cross Browser Testing");
        caps.setCapability("browserName", browser);
        caps.setCapability("version", version);
        caps.setCapability("platform", platform);
        caps.setCapability("network", true);
        caps.setCapability("console", true);
        caps.setCapability("visual", true);

        try {
            driver = new RemoteWebDriver(new URL("https://" +
                    username + ":" + accessKey + hub), caps);
        }
        catch (MalformedURLException exc) {
            exc.printStackTrace();
        }*/

        if(browser.equals("chrome")) {
            driver = new ChromeDriver();
        }
        else if(browser.equals("edge")) {
            driver = new EdgeDriver();
        }
        else {
            driver = new ChromeDriver();
        }

        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @Test
    public void testDropDowns() {
        driver.findElement(By.linkText("Select Dropdown List")).click();
        WebElement findDropDown = driver.findElement(By.id("select-demo"));

        Select dayDropDown = new Select(findDropDown);
        dayDropDown.selectByVisibleText("Sunday");
    }

    @Test
    public void testDragAndDrop() {
        driver.findElement(By.linkText("Drag and Drop")).click();
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement targets = driver.findElement(By.id("droppable"));

        Actions act = new Actions(driver);
        act.dragAndDrop(source, targets).perform();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
