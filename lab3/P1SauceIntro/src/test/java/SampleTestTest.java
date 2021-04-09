import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.*;

class SampleTestTest {

    WebDriver browser;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // gecko is on system path so no settings are needed
        // System.setProperty("webdriver.gecko.driver", "....");

        // Preferences
        FirefoxOptions options = new FirefoxOptions().addPreference("browser.startup.homepage", "https://www.ua.pt");
        browser = new FirefoxDriver(options);
    }


    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        browser.close();
    }

    @Test
    public void site_header_is_on_home_page() {
        browser.get("https://www.saucelabs.com");
        WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));
        assertTrue((href.isDisplayed()));

    }

}