package com.branch.Tests;

import com.branch.team.TeamAction;
import com.branch.team.TeamMember;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;

public class TeamInfoTest extends BaseTest{
    private TeamAction teamAction;
    private static final Logger logger = LoggerFactory.getLogger(TeamInfoTest.class);
    private List<TeamMember> allTabMembersList;
    private Map<String,List<TeamMember>> eachTeamMembersMap;
    private List<TeamMember> eachTeamMembersList;

    @BeforeClass
    public void beforeClass(){
        setUp();
        teamAction = new TeamAction(driver);
        teamAction.searchInGoogle("Branch.io");
        teamAction.openBranchSite();
        teamAction.getBranchTeam();
        allTabMembersList =  teamAction.getAllTabVOList();
        eachTeamMembersMap = teamAction.getEachTeamMembersVOList();
        eachTeamMembersList = teamAction.getListOfEachTabMembers(eachTeamMembersMap);
    }
    @Test(description = "Test whether team member's name match between All tab and each department tabs")
    public void employeesNameMatchTest(){
        List<String> allTabMembersName = allTabMembersList.stream().map(member -> member.getName()).collect(Collectors.toList());
        List<String> eachTabMembersName = eachTeamMembersList.stream().map(member -> member.getName()).collect(Collectors.toList());
        List<String> nameMismatchMembersList = eachTabMembersName.stream().filter(
                member -> !allTabMembersName.contains(member)).collect(Collectors.toList());
        Assert.assertTrue(nameMismatchMembersList.size() == 0,"Names did not match between all tab and each tab");
        logger.info("Number of members with name mismatch is "+nameMismatchMembersList.size());
    }
    @Test(description = "Test whether team member's department assignment match between All tab and each department tabs")
    public void employeesDepartmentMatchTest(){
        Map<String,String> allTabMembersDeptMap = allTabMembersList.stream().collect(
                Collectors.toMap(teamMember -> teamMember.getName(), teamMember -> teamMember.getDepartment()));
        Map<String,String> eachTabMembersDeptMap = eachTeamMembersList.stream().collect(
                Collectors.toMap(teamMember -> teamMember.getName(),teamMember -> teamMember.getDepartment()));
        List<String> deptMismatchMembersList = eachTabMembersDeptMap.keySet().stream().filter(
                member -> !allTabMembersDeptMap.get(member).equals(eachTabMembersDeptMap.get(member))).collect(Collectors.toList());
        Assert.assertTrue(deptMismatchMembersList.size() == 0,"Department of members did not match between all tab and each tab");
    }
    @Test(description = "Test whether team member's images match between All tab and each department tabs")
    public void employeesImageMatchTest(){
        Map<String,String> allTabMembersImageUrlMap = allTabMembersList.stream().collect(
                Collectors.toMap(teamMember -> teamMember.getName(), teamMember -> teamMember.getImageUrl()));
        Map<String,String> eachTabMembersImageUrlMap = eachTeamMembersList.stream().collect(
                Collectors.toMap(teamMember -> teamMember.getName(),teamMember -> teamMember.getImageUrl()));
        List<String> imageMismatchMembersList = eachTabMembersImageUrlMap.keySet().stream().filter(
                member -> !allTabMembersImageUrlMap.get(member).equals(eachTabMembersImageUrlMap.get(member))).collect(Collectors.toList());
        Assert.assertTrue(imageMismatchMembersList.size() == 0,"Image of members did not match between all tab and each tab");
    }
    @Test(description = "Test whether team member's profile link match between All tab and each department tabs")
    public void profileLinkMatchTest(){
        Map<String,String> allTabMembersProfileLinkMap = allTabMembersList.stream().filter(
                teamMember -> !teamMember.getProfileLink().isEmpty()).collect(
                Collectors.toMap(teamMember -> teamMember.getName(), teamMember -> teamMember.getProfileLink()));
        Map<String,String> eachTabMembersProfileLinkMap = eachTeamMembersList.stream().filter(
                teamMember -> !teamMember.getProfileLink().isEmpty()).collect(
                Collectors.toMap(teamMember -> teamMember.getName(),teamMember -> teamMember.getProfileLink()));
        List<String> profileLinkMismatchMembersList = eachTabMembersProfileLinkMap.keySet().stream().filter(
                member -> !allTabMembersProfileLinkMap.get(member).equals(eachTabMembersProfileLinkMap.get(member))).collect(
                        Collectors.toList());
        Assert.assertTrue(profileLinkMismatchMembersList.size() == 0,"Profile Link did not match between all tab and each tab");
    }
    @AfterClass
    public void afterClass(){
        closeWindow();
    }
}
