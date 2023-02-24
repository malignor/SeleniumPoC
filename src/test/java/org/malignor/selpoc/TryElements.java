package org.malignor.selpoc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

//https://demoqa.com/text-box

public class TryElements {
    UseCaseDataManager qademoData = new UseCaseCSVManager();
    String resourceFile = "TestData-001.csv";
    WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    void setUsUp(){
        String fullpath = "/"+resourceFile;
        System.out.println("Data file path = "+fullpath);
        qademoData.setSource(fullpath);
        qademoData.loadData();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    void naveToElements(){
//        WebDriver driver = new ChromeDriver();

        // Test name: TextBoxes
        // Step # | name | target | value
        // 1 | open | / |
        driver.get("https://demoqa.com/");
        // 2 | setWindowSize | 1191x799 |
        driver.manage().window().setSize(new Dimension(1191, 799));
        // 3 | click | css=.card:nth-child(1) svg |
        driver.findElement(By.cssSelector(".card:nth-child(1) svg")).click();
        // 4 | click | css=.show #item-0 > .text |
        driver.findElement(By.cssSelector(".show #item-0 > .text")).click();
        // 5 | click | id=userName |
        driver.findElement(By.id("userName")).click();
        // 6 | type | id=userName | TextBox Fullname
        driver.findElement(By.id("userName")).sendKeys("TextBox Fullname");
        // 7 | type | id=userEmail | email@nowhere.com
        driver.findElement(By.id("userEmail")).sendKeys("email@nowhere.com");
        // 8 | click | id=currentAddress |
        driver.findElement(By.id("currentAddress")).click();
        // 9 | type | id=currentAddress | here at current address
        driver.findElement(By.id("currentAddress")).sendKeys("here at current address");
        // 10 | type | id=permanentAddress | perma-address
        driver.findElement(By.id("permanentAddress")).sendKeys("perma-address");
        // 11 | click | id=submit |
        driver.findElement(By.id("submit")).click();

    }

    @AfterSuite(alwaysRun = true)
    void shutDown(){
        driver.quit();
    }

}
