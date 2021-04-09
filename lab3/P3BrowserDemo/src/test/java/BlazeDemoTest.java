import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
public class BlazeDemoTest {

    @Test
    public void blaze_test(FirefoxDriver firefoxDriver) {
        firefoxDriver.get("https://blazedemo.com/");
        firefoxDriver.manage().window().setSize(new Dimension(550, 692));
        firefoxDriver.findElement(By.cssSelector("h1")).click();
        assertEquals(firefoxDriver.findElement(By.cssSelector("h1")).getText(), "Welcome to the Simple Travel Agency!");
        {
            WebElement dropdown = firefoxDriver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'Boston']")).click();
        }
        firefoxDriver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(3)")).click();
        {
            WebElement dropdown = firefoxDriver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'New York']")).click();
        }
        firefoxDriver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(5)")).click();
        firefoxDriver.findElement(By.cssSelector(".btn-primary")).click();
        firefoxDriver.findElement(By.cssSelector("html")).click();
        assertEquals(firefoxDriver.findElement(By.cssSelector("h3")).getText(), "Flights from Boston to New York:");
        firefoxDriver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
        firefoxDriver.findElement(By.id("inputName")).click();
        firefoxDriver.findElement(By.id("inputName")).sendKeys("Hugo Almeida");
        firefoxDriver.findElement(By.id("address")).click();
        firefoxDriver.findElement(By.id("address")).sendKeys("Universidade Aveiro");
        firefoxDriver.findElement(By.id("city")).sendKeys("Aveiro");
        firefoxDriver.findElement(By.id("state")).sendKeys("Aveiro");
        firefoxDriver.findElement(By.id("zipCode")).click();
        firefoxDriver.findElement(By.id("zipCode")).sendKeys("3810-193");
        firefoxDriver.findElement(By.id("creditCardNumber")).click();
        firefoxDriver.findElement(By.id("creditCardNumber")).sendKeys("121212212123123123213123123123123123123123");
        firefoxDriver.findElement(By.id("nameOnCard")).click();
        firefoxDriver.findElement(By.id("nameOnCard")).sendKeys("Hugo Almeida");
        firefoxDriver.findElement(By.cssSelector(".btn-primary")).click();
        firefoxDriver.findElement(By.cssSelector(".hero-unit")).click();
        assertEquals(firefoxDriver.findElement(By.cssSelector("h1")).getText(), "Thank you for your purchase today!");
        assertEquals(firefoxDriver.getTitle(), "BlazeDemo Confirmation");
    }

}
