import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

@ExtendWith(SeleniumJupiter.class)
class ChromeAndFirefoxTest {

    ChromeDriver driver;

    // Dependency Injection of browser in Constructor
    ChromeAndFirefoxTest(ChromeDriver driver) {
        this.driver = driver;
    }

    @Test
    void testGlobalChrome() {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

    // Dependency Injection of browser in parameters
    @Test
    void testWithChrome(ChromeDriver chromeDriver) {
        chromeDriver.get("https://pplware.sapo.pt");
        assertThat(chromeDriver.getTitle(),
                is("Pplware"));
    }

    @Test
    void testWithFirefox(FirefoxDriver firefoxDriver) {
        firefoxDriver.get("https://www.google.com/");
        assertThat(firefoxDriver.getTitle(),
                is("Google"));
    }

    @Test
    void testWithChromeAndFirefox(ChromeDriver chromeDriver,
                                  FirefoxDriver firefoxDriver) {
        chromeDriver.get("https://www.google.com/");
        assertThat(chromeDriver.getTitle(),
                is("Google"));

        firefoxDriver.get("https://pplware.sapo.pt");
        assertThat(firefoxDriver.getTitle(),
                is("Pplware"));

    }

    @Test
    void testWithSafari(SafariDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.get("https://www.apple.com");
        assertTrue(wait.until(ExpectedConditions.titleIs("Apple")));
    }

}