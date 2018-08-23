package com.branch.Tests;

import com.branch.team.TeamAction;
import com.branch.team.TeamMember;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.Dimension;
import java.util.List;
import java.util.Map;

public class TeamMembersCountMatchTest extends BaseTest {
   private TeamAction teamAction;

   @BeforeClass
   public void beforeClass(){
       setUp();
       teamAction = new TeamAction(driver);
   }
   @Test(dataProvider = "screenDimension",description = "Test whether team members count match between All tab and each department tabs")
   public void employeesCountTest(Dimension dimension){
       setDimension(dimension);
       teamAction.searchInGoogle("Branch.io");
       teamAction.openBranchSite();
       teamAction.getBranchTeam();
       int allMembersCount = teamAction.getAllTabCount();
       Map<String,List<TeamMember>> eachTeamMembersMap = teamAction.getEachTeamMembersVOList();
       int eachTeamsCountSum = teamAction.getEachTeamMembersCountFromVOList(eachTeamMembersMap);
       Assert.assertEquals(eachTeamsCountSum,allMembersCount,"Team members count mismatch between All tabs and Sum of other tabs");
   }
   @AfterClass
   public void afterClass(){
        closeWindow();
   }
}
