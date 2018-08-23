package com.branch.team;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class TeamAction {
    private TeamView tabView;
    private WebDriver driver;
    public TeamAction(WebDriver driver){
        this.driver = driver;
        tabView = new TeamView(driver);
    }
    public void searchInGoogle(String searchKey){
        tabView.openGoogle();
        WebElement element = tabView.getGoogleSearchTextBox();
        element.sendKeys(searchKey);
        element.submit();
    }
    public void openBranchSite(){
        tabView.getBranchPageFromSearch().click();
    }
    public void getBranchTeam(){
        tabView.scrollIntoView(tabView.getFooter());
        if(driver.manage().window().getSize().getWidth()<851)
        {
            tabView.getCompanyLink().click();
            await().atMost(20, TimeUnit.SECONDS).until(()->tabView.getBranchTeamLink().isDisplayed());
            tabView.getBranchTeamLink().click();
        }
        else{
            tabView.getDesktopBranchTeamLink().click();
        }
    }
    public int getAllTabCount(){
        return getAllTabVOList().size();
    }
    public List<TeamMember> getAllTabVOList(){
        tabView.scrollIntoView(tabView.getTeamCategories());
        tabView.scrollBySize(-500);
        tabView.getAllTabLink().click();
        return createTeamMemberVO(tabView.getAllTeamMembers());
    }
    public Map<String,List<TeamMember>> getEachTeamMembersVOList(){
        List<WebElement> getAllCategoriesList = tabView.getTeamCategoriesList();
        Map<String,List<TeamMember>> eachTeamMembersMap = new HashMap<>();
        for(WebElement webs : getAllCategoriesList){
            String teamName = tabView.getTeamName(webs);
            if(teamName.equals("all"))
                continue;
            tabView.getTeamLink(webs).click();
            List<WebElement> currentTeamMembers = tabView.getEachTeamMembers(teamName);
            eachTeamMembersMap.put(teamName,createTeamMemberVO(currentTeamMembers));
        }
        return eachTeamMembersMap;
    }
    public int getEachTeamMembersCountFromVOList(Map<String,List<TeamMember>> eachTeamMembersMap){
        int sum = 0;
        for (List<TeamMember> value : eachTeamMembersMap.values()) {
            sum+=value.size();
        }
        return sum;
    }
    public List<TeamMember> getListOfEachTabMembers(Map<String,List<TeamMember>> eachTeamMembersMap){
        List<TeamMember> eachTabMembersList = new LinkedList<>();
        for (List<TeamMember> value : eachTeamMembersMap.values()) {
            eachTabMembersList.addAll(value);
        }
        return eachTabMembersList;
    }
    public List<TeamMember> createTeamMemberVO(List<WebElement> webElementList){
        List<TeamMember> teamMemberList = new LinkedList<>();
        for(WebElement memberWebElement : webElementList){
            TeamMember member = new TeamMember();
            member.setName(tabView.getMemberName(memberWebElement));
            member.setDepartment(tabView.getMemberDepartment(memberWebElement));
            member.setImageUrl(tabView.getMemberImageUrl(memberWebElement));
            member.setProfileLink(tabView.getProfileLink(memberWebElement));
            teamMemberList.add(member);
        }
        return teamMemberList;
    }
}
