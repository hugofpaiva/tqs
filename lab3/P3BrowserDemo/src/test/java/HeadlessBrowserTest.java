import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SeleniumJupiter.class)
public class HeadlessBrowserTest {

    PhantomJSDriver driver;

    HeadlessBrowserTest(PhantomJSDriver driver) {
        this.driver = driver;
    }

    @Test
    void testGlobalPhantomJS() {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

    @Test
    void testWithPhantomJS(PhantomJSDriver driver) {
        // Source code of the page which is stored as string
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
                assertNotNull(driver.getPageSource());
    }

    @Test
    void testWithHTMLUnit(HtmlUnitDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
                assertThat(driver.getTitle(), containsString("JUnit 5 extension for Selenium"));
    }
}
