package com.branch.team;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseView {
    public WebDriver driver;
    JavascriptExecutor je;

    public BaseView (WebDriver driver){
        this.driver = driver;
        je = (JavascriptExecutor) driver;
    }
    public void scrollIntoView(WebElement element){
        je.executeScript("arguments[0].scrollIntoView(true);",new Object[]{element});
    }
    public void scrollBySize(int size){
        je.executeScript("window.scrollBy(0,"+size+")");
    }
}
