import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BlazeDemoTest {

    WebDriver browser;

    @BeforeEach
    void setUp() {
        // Preferences
        FirefoxOptions options = new FirefoxOptions().addPreference("browser.startup.homepage", "https://www.ua.pt");
        browser = new FirefoxDriver(options);
    }

    @AfterEach
    void tearDown() {
        browser.close();
    }

    @Test
    public void blaze_test() {
        browser.get("https://blazedemo.com/");
        browser.manage().window().setSize(new Dimension(550, 692));
        browser.findElement(By.cssSelector("h1")).click();
        assertEquals(browser.findElement(By.cssSelector("h1")).getText(), "Welcome to the Simple Travel Agency!");
        {
            WebElement dropdown = browser.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'Boston']")).click();
        }
        browser.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(3)")).click();
        {
            WebElement dropdown = browser.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'New York']")).click();
        }
        browser.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(5)")).click();
        browser.findElement(By.cssSelector(".btn-primary")).click();
        browser.findElement(By.cssSelector("html")).click();
        assertEquals(browser.findElement(By.cssSelector("h3")).getText(), "Flights from Boston to New York:");
        browser.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
        browser.findElement(By.id("inputName")).click();
        browser.findElement(By.id("inputName")).sendKeys("Hugo Almeida");
        browser.findElement(By.id("address")).click();
        browser.findElement(By.id("address")).sendKeys("Universidade Aveiro");
        browser.findElement(By.id("city")).sendKeys("Aveiro");
        browser.findElement(By.id("state")).sendKeys("Aveiro");
        browser.findElement(By.id("zipCode")).click();
        browser.findElement(By.id("zipCode")).sendKeys("3810-193");
        browser.findElement(By.id("creditCardNumber")).click();
        browser.findElement(By.id("creditCardNumber")).sendKeys("121212212123123123213123123123123123123123");
        browser.findElement(By.id("nameOnCard")).click();
        browser.findElement(By.id("nameOnCard")).sendKeys("Hugo Almeida");
        browser.findElement(By.cssSelector(".btn-primary")).click();
        browser.findElement(By.cssSelector(".hero-unit")).click();
        assertEquals(browser.findElement(By.cssSelector("h1")).getText(), "Thank you for your purchase today!");
        assertEquals(browser.getTitle(), "BlazeDemo Confirmation");
    }
}