package org.malignor.selpoc;

// from https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/java/src/test/java/dev/selenium/getting_started
// look at https://testng.org/doc/selenium.html
// see also https://medium.com/@knoldus/testng-setup-using-maven-for-selenium-7bff01a443a7

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InstallDriversTest {
    @DataProvider(name = "accountLogins")
    public Object[][] dpMethod() {
        return new Object [][] {{"username"},{"login"}};
    }

    @BeforeSuite(alwaysRun = true)
    public void setupContext(ITestContext context){

    }

    @Test
    public void chromeSession() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.quit();
    }

    @Test
    public void edgeSession() {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.quit();
    }

    @Test
    public void firefoxSession() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.quit();
    }

}
