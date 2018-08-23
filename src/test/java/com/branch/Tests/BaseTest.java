package com.branch.Tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    public String browser;

    public BaseTest(){
        browser = "chrome";
    }
    public void setUp() {
        if (browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","/Users/Poorvi/IdeaProjects/branchAssignment/src/main/resources/drivers/chromedriver");
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private static Dimension Mobile = new Dimension(480,800);
    private static Dimension Desktop = new Dimension(1200,800);
    private static  Dimension Tablet = new Dimension(850, 800);

    @DataProvider(name = "screenDimension")
    public static Object[][] screenDimension(){
        Dimension[][] dimension = new Dimension[3][1];
        dimension[0][0] = Mobile;
        dimension[1][0] = Tablet;
        dimension[2][0] = Desktop;
        return dimension;
    }
    public void setDimension(Dimension dimension){
        driver.manage().window().setSize(dimension);
    }
    public void closeWindow(){
        driver.close();
    }
}
