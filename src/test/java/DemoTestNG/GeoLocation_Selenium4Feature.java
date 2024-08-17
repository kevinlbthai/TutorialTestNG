package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GeoLocation_Selenium4Feature {
    ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void mockGeoLocation_executeCDPCommand() {
        // Define geolocation coordinates
        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("latitude", 25.1972); // Specify the latitude
        coordinates.put("longitude", 55.2744); // Specify the longitude
        coordinates.put("accuracy", 1); // Specify the accuracy
        // Emulate geolocation
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

        driver.get("https://where-am-i.org/");
    }
}
