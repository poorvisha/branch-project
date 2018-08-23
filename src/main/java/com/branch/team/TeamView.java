package com.branch.team;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TeamView extends BaseView {

    public TeamView(WebDriver driver){
        super(driver);
    }

    public void openGoogle(){
        driver.get("http://www.google.com");
    }
    public WebElement getGoogleSearchTextBox(){
       return driver.findElement(By.name("q"));
    }
    public WebElement getBranchPageFromSearch(){
        return driver.findElement(By.linkText("Branch.io"));
    }
    public WebElement getFooter(){
        return driver.findElement(By.xpath("//div[contains(@class,'footer-inner')]"));
    }
    public WebElement getDesktopBranchTeamLink(){
        return getFooter().findElement(By.xpath("//a[@data-element-tag='team']"));
    }
    public WebElement getBranchTeamLink(){
        return getCompanyLink().findElement(By.xpath("//a[@data-element-tag='team']"));
    }
    public WebElement getCompanyLink(){
        return getFooter().findElement(By.xpath("//a[contains(@class,'bds-accordian-heading') and contains(text(),'Company')]"));
    }
    public List<WebElement> getTeamCategoriesList(){
        return getTeamCategories().findElements(By.tagName("li"));
    }
    public WebElement getTeamContainer(){
        return driver.findElement(By.xpath("//div[contains(@class,'container-fluid teams-container')]"));
    }
    public WebElement getTeamCategories(){
        return getTeamContainer().findElement(By.xpath("//ul[contains(@class,'team-categories')]"));
    }
    public WebElement getAllTabLink(){
        return getTeamCategories().findElement(By.xpath("//a[@href='#all']"));
    }
    public List<WebElement> getAllTeamMembers(){
        return getTeamCategories().findElements(By.xpath("//div[contains(@class,'category-all') and (contains(@style,'display: inline-block'))]"));
    }
    public List<WebElement> getEachTeamMembers(String teamName){
        return getTeamCategories().findElements(By.xpath("//div[contains(@class,'category-"+teamName+"') and (contains(@style,'display: inline-block'))]"));
    }
    public String getTeamName(WebElement element){
        return element.findElement(By.tagName("a")).getAttribute("rel");
    }
    public WebElement getTeamLink(WebElement element){
        return element.findElement(By.tagName("a"));
    }
    public WebElement getInfoBlock(WebElement element){
        return element.findElement(By.xpath("//div[contains(@class,'info-block')]"));
    }
    public WebElement getImageBlock(WebElement element){
        return element.findElement(By.xpath("//div[contains(@class,'image-block')]"));
    }
    public String getMemberName(WebElement element){
        return element.findElement(By.tagName("h2")).getText();
    }
    public String getMemberDepartment(WebElement element){
        return element.findElement(By.tagName("h4")).getText();
    }
    public String getMemberImageUrl(WebElement element) {
        String imageUrlAttr = element.findElement(By.xpath("//div[contains(@class,'image-block')]")).getAttribute("style");
        imageUrlAttr = imageUrlAttr.substring(imageUrlAttr.indexOf('(')+2,imageUrlAttr.indexOf(')')-1);
        return imageUrlAttr;
    }
    public String getProfileLink(WebElement element){
        return element.findElement(By.xpath("//div[contains(@class,'info-block')]//a[contains(@class,'profile-link')]")).getAttribute("href");
    }
}
